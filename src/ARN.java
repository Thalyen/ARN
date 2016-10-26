public class ARN{

	//Atributos da árvore
	No raiz = null;
	int qtd;

	public ARN(int chave){//Construtores
		raiz = new No(null, chave);
		qtd = 1;
	}

	public ARN(){//Construtores

	}

	public No getRaiz(){// Retorna a raiz da árvore 
		return raiz;
	}

	public void setRaiz(No raiz){// insere raiz
		this.raiz = raiz;
	}

	public boolean isInternal(No no){// Testa se um No é interno 
		return (no.hasFilhoDir() || no.hasFilhoEsq());
	}

	public boolean isExternal(No no){// Testa se um No é externo
		return (!no.hasFilhoDir() && !no.hasFilhoEsq());
	}

	public boolean isRoot(No no){// Testa se um No é a raiz 
		return no == raiz;
	}

	public void swapChave(No v, No w){// Troca dois elementos de posição 
		int aux = v.getChave();
		v.setChave(w.getChave());
		w.setChave(aux);
	}

	public int depth(No no){// Retorna a profundidade de um No 
		int profundidade = profundidade(no);
		return profundidade;
	}

	private int profundidade(No no){// soma a profundidade de todos os nos
		if(no == raiz)
			return 0;
		else
			return 1 + profundidade(no.getPai());
	}

	public int height(){// Retorna a altura da árvore 
		int altura = 0;
		return altura;
	}

	public int size(){// Retorna o número de Nos da árvore 
		return qtd;
	}

	public boolean isEmpty(){// Retorna se a ávore está vazia. Sempre vai ser falso, pois não permitimos remover a raiz
		return false;
	}

	public Object replace(No v, int o){
		v.setChave(o);
		return null;
	}

	public void inOrder(No no){// caminha na arvore inorder
		if(no != null){
			inOrder(no.getFilhoEsq());
			System.out.print(no.getChave() + " ");
			inOrder(no.getFilhoDir());
		}
	}

	public void preOrder(No no){// caminha na arvore preorder
		if(no != null){
			System.out.println(no.getChave() + ": " + no.getCor());
			preOrder(no.getFilhoEsq());
			preOrder(no.getFilhoDir());
		}
	}

	/*public  void imprimir(No no){
		if(isRoot(no)){
			System.out.println(no.getChave());
			System.out.println(no.getFilhoEsq().getChave() + " " + no.getFilhoDir().getChave());
		}else{
			System.out.println(no.getFilhoEsq().getChave() + " " + no.getFilhoDir().getChave());
			imprimir(no.getFilhoEsq());
			imprimir(no.getFilhoDir());
		}
	}*/

	public No busca(int chave, No no){// retorna um no msm sem ter encontrado a chave
		if(isExternal(no))
			return no;
		else if(isInternal(no)){
			if( chave == no.getChave())
				return no;
			else if(chave < no.getChave() && no.hasFilhoEsq())
				return busca(chave, no.getFilhoEsq());
			else if(chave > no.getChave()&& no.hasFilhoDir())
				return busca(chave, no.getFilhoDir());
		}
		return no;
	}

	public No pesquisa(int chave)throws InvalidValueException{// so retorna um no se igual a chave
		if(busca(chave, getRaiz()).getChave() != chave)
			throw new InvalidValueException("\nERROR: Valor não encontrado\n");
		return busca(chave,getRaiz());
	}

	public void familia(No no){//retorna pai e filhos de um no
		if (isRoot(no))
			System.out.println( "Raiz: " + no.getChave() + " FB: " + no.getFB() + " cor: " + no.getCor());
		else
			System.out.println("Pai: " + no.getPai().getChave() + " FB: " + no.getPai().getFB() + " cor: " + no.getCor());

		if (no.hasFilhoDir())
			System.out.println("FilhoD: " + no.getFilhoDir().getChave() + " FB: " + no.getFilhoDir().getFB() + " cor: " + no.getCor());
		if (no.hasFilhoEsq())
			System.out.println("FilhoE:" + no.getFilhoEsq().getChave() + " FB: " + no.getFilhoEsq().getFB() + " cor: " + no.getCor());
		System.out.println();
	}

	public void insere(int chave) {  //insere um no na árvore
		No novo = new No(chave);

		if(raiz == null){  //se nao existir nó cria um novo  
			novo = new No(null, chave); 
			novo.setFB(0);
			novo.setCor("Negro");
			novo.setDuploNegro(0);
			setRaiz(novo);
			qtd ++;
		}else{
			No pai = busca(chave, raiz);

			if(pai != null && chave < pai.getChave()){ 
				novo.setChave(chave);
				novo.setPai(pai);
				novo.setFB(0);
				novo.setCor("Rubro");
				novo.setDuploNegro(0);
				pai.setFilhoEsq(novo);// menor que nó, insere na esquerda  
				qtd ++;
			} else if(pai != null && chave > pai.getChave()){ 
				novo.setChave(chave);
				novo.setPai(pai);
				novo.setFB(0);
				novo.setCor("Rubro");
				novo.setDuploNegro(0);
				pai.setFilhoDir(novo);// menor que nó, insere na esquerda  
				qtd ++;
			} 
			else{// valor repetido 
				System.out.println("\nERRO: valor já existe na árvore.\n");  
			}  
			atualizaCor(novo);
			atualizarFB(novo, 1);
		}
	}  

	public No remove(int chave) throws EmptyTreeException, InvalidValueException {// remove um no
		No velho = pesquisa(chave);
		No sucessor = null;
		int aux; 

		if (velho == getRaiz() && isExternal(velho) || velho == null)
			throw new EmptyTreeException("\nERROR: Valor inválido\n");//arvore vazia ou raiz sem filho
		else{
			if(isExternal(velho)){//se o removido for folha

				atualizarRemoverRN(velho);
				atualizarFB(velho, -1);

				if (velho.getPai().getFilhoEsq() == velho)//folha esquerda
					velho.getPai().setFilhoEsq(null);
				else if(velho.getPai().getFilhoDir() == velho)//folha direita
					velho.getPai().setFilhoDir(null);

				delete(velho);

			}else{
				if(velho.hasFilhoDir() && velho.hasFilhoEsq()){
					sucessor = sucessor(velho.getFilhoDir());  //se o no removido tiver 2 filhos

					atualizarRemoverRN(velho);
					atualizarFB(velho, -1);
					aux =  sucessor.getChave();
					remove(sucessor.getChave());
					velho.setChave(aux);

				}else if(velho.hasFilhoDir() && !velho.hasFilhoEsq()){// removido só tem filho direito
					sucessor = velho.getFilhoDir();

					atualizarRemoverRN(velho);
					atualizarFB(velho, -1);
					if(velho.getPai().getFilhoDir() == velho){
						velho.getPai().setFilhoDir(sucessor);
						sucessor.setPai(velho.getPai());
					}else if(velho.getPai().getFilhoEsq() == velho){
						velho.getPai().setFilhoEsq(sucessor);
						sucessor.setPai(velho.getPai());
					}
					delete(velho);

				}else if(!velho.hasFilhoDir() && velho.hasFilhoEsq()){// removido só tem filho esquerdo
					sucessor = velho.getFilhoEsq();

					atualizarRemoverRN(velho);
					atualizarFB(velho, -1);
					if(velho.getPai().getFilhoDir() == velho){
						velho.getPai().setFilhoDir(sucessor);
						sucessor.setPai(velho.getPai());
					}else if(velho.getPai().getFilhoEsq() == velho){
						velho.getPai().setFilhoEsq(sucessor);
						sucessor.setPai(velho.getPai());
					}
					delete(velho);
				}
			}
			qtd --;
			return velho;
		}
	}

	public No sucessor(No no){// retorna ultimo no esq da arvore ou ele msm
		if(no.hasFilhoEsq()) {  
			return sucessor(no.getFilhoEsq());
		}  
		return no;
	}

	public No delete(No no){//anula as referencias
		no.setPai(null);
		no.setFilhoDir(null);
		no.setFilhoEsq(null);
		return no;
	}

	public int atualizarFB(No no, int func){//retorna o Fator de Balanceamento

		if(no.getPai() == null){
			return no.getFB();
		}else{
			if(func == 1){// inserção-----------------------
				if (no.getPai().getFilhoEsq() == no){
					no.getPai().setFB(no.getPai().getFB()+(1*func));
				}else if (no.getPai().getFilhoDir() == no){
					no.getPai().setFB(no.getPai().getFB()+(-1*func));
				}


				if(no.getPai().getFB() != 0){
					return atualizarFB(no.getPai(), func);
				}
			}else if(func == -1){// remoção----------------
				if (no.getPai().getFilhoEsq() == no){
					no.getPai().setFB(no.getPai().getFB()-(-1)*func);
				}else if (no.getPai().getFilhoDir() == no){
					no.getPai().setFB(no.getPai().getFB()-(1*func));
				}

				if(no.getPai().getFB() == 0){
					return atualizarFB(no.getPai(), func);
				}
			}
			return no.getFB();
		} 
	}

	public void atualizaCor(No no){

		if(no == raiz || no.getPai() == raiz || no.getAvo() == null){// sit 1
			return;
		}else if(no.isRubro() && no.getPai().isRubro() ){//sit 2
			if(no.getAvo().isNegro() && no.hasTio() && no.getTio().isRubro()){// tio rubro
				no.getAvo().setCor("Rubro");
				no.getTio().setCor("Negro");
				no.getPai().setCor("Negro");

			}else if(no.getAvo().isNegro() && !no.hasTio() || no.getTio().isNegro()){// sem tio ou tio negro

				if(no.getPai() == no.getAvo().getFilhoDir() && no.getPai().getFilhoDir() == no){
					no.getPai().setCor("Negro");
					no.getAvo().setCor("Rubro");

					if(no.getAvo() == raiz || no.getAvo().getPai().getFilhoDir() == no.getAvo())// RSE-----
						rotacaoSE(no.getAvo(), -1);
					else
						rotacaoSE(no.getAvo(), 1);

				}else if(no.getPai() == no.getAvo().getFilhoEsq() && no.getPai().getFilhoEsq() == no){
					no.getPai().setCor("Negro");
					no.getAvo().setCor("Rubro");

					if(no.getAvo().getPai().getFilhoDir() == no.getAvo() || no.getAvo() == raiz)// RSD--------
						rotacaoSD(no.getAvo(), -1);
					else
						rotacaoSD(no.getAvo(), 1);

				}else if(no.getPai() == no.getAvo().getFilhoDir() && no.getPai().getFilhoEsq() == no){
					no.setCor("Negro");
					no.getAvo().setCor("Rubro");

					if(no.getAvo().getPai().getFilhoDir() == no.getAvo()){// RDE--------
						no = rotacaoSD(no.getPai(), -1);// avo filho dir
						rotacaoSE(no.getPai(), -1);
					}else{
						no = rotacaoSE(no.getPai(), -1);
						rotacaoSD(no.getPai(), 1);
					}

				}else if(no.getPai() == no.getAvo().getFilhoEsq() && no.getPai().getFilhoDir() == no){
					no.setCor("Negro");
					no.getAvo().setCor("Rubro");

					if(no.getAvo().getPai().getFilhoDir() == no.getAvo()){// RDD--------
						no = rotacaoSE(no.getPai(), 1);// avo filho dir
						rotacaoSD(no.getPai(), -1);
					}else{
						no = rotacaoSE(no.getPai(), 1);
						rotacaoSD(no.getPai(), 1);
					}
				}
			}	
		}
		raiz.setCor("Negro");// garante raiz negra
	}

	public No balanceamentoFB(No no){
		No rotar= null;

		if(no.getFB() <= -3){//rotação à esquerda------------

			if(no.getFilhoDir().getFB() == -1){// simples-----
				if(no == raiz )//se no for raiz
					no = rotacaoSE(no, 0);
				else if(no.getPai().getFilhoDir() == no )
					no = rotacaoSE(no, -1);
				else if(no.getPai().getFilhoEsq() == no )//filho esq
					no = rotacaoSE(no, 1);

			}else if(no.getFilhoDir().getFB() == 1){//dupla----
				rotar = rotacaoSD(no.getFilhoDir(), -1);
				no = rotacaoSE(rotar.getPai(), -1);
			}

		}else if(no.getFB() >= 3){//rotação à direita-------------------------

			if(no.getFilhoEsq().getFB() == 1){// simples-----
				if(no == raiz )//se no for raiz
					no = rotacaoSD(no, 0);
				else if(no.getPai().getFilhoEsq() == no)//filho  esq
					no = rotacaoSD(no, 1);
				else
					no = rotacaoSD(no, -1);

			}else if(no.getFilhoEsq().getFB() == -1){//dupla-----
				rotar = rotacaoSE(no.getFilhoEsq(), 1);
				no = rotacaoSD(rotar.getPai(), 1);
			}
		}
		return no;
	}

	public No rotacaoSE(No noB, int s){//simples esquerda _ s é pra saber se é filho esq ou dir
		No aux = noB;
		No no = aux.getFilhoDir();
		no.setPai(aux.getPai());

		if (aux.getPai() == null ){
			aux.setFilhoDir(null);
			setRaiz(no);
		}else if(aux.getPai() != null){
			if(s == 1)
				aux.getPai().setFilhoEsq(no);
			else 	if(s == -1)
				aux.getPai().setFilhoDir(no);
		}

		if(no.getFilhoEsq() != null){
			no.getFilhoEsq().setPai(aux);
			aux.setFilhoDir(no.getFilhoEsq());
		}else{
			aux.setFilhoDir(null);
		}
		no.setFilhoEsq(aux);
		aux.setPai(no);

		if (aux.getFB() == -2){//atualiza fb
			if(no.getFB() == 0){
				aux.setFB(-1);
				no.setFB(1);
			}else if(no.getFB() == -2){
				aux.setFB(1);
				no.setFB(0);
			}else{
				aux.setFB(0);
				no.setFB(0);
			}
		}else{
			if(no.getFB() == -1){
				no.setFB(1);
				aux.setFB(1);
			}else if(no.getFB() == 0){
				no.setFB(1);
				aux.setFB(0);
			}else{
				no.setFB(2);
				aux.setFB(0);
			}
		}
		return no;
	}

	public No rotacaoSD(No noB, int s){//simples direita_ s é pra saber se é filho esq ou dir
		No aux = noB;
		No no = aux.getFilhoEsq();
		no.setPai(aux.getPai());

		if (aux.getPai() == null ){
			aux.setFilhoEsq(null);
			setRaiz(no);
		}else if(aux.getPai() != null){
			if(s == 1)
				aux.getPai().setFilhoEsq(no);
			else 	if(s == -1)
				aux.getPai().setFilhoDir(no);
		}

		if(no.getFilhoDir() != null){
			no.getFilhoDir().setPai(aux);
			aux.setFilhoEsq(no.getFilhoDir());
		}else{
			aux.setFilhoEsq(null);
		}
		no.setFilhoDir(aux);
		aux.setPai(no);

		if (aux.getFB() == 2){//atualiza fb
			if(no.getFB() == 0){
				aux.setFB(1);
				no.setFB(-1);
			}else if(no.getFB() == -1){
				aux.setFB(-1);
				no.setFB(-2);
			}else{
				aux.setFB(0);
				no.setFB(0);
			}
		}else{
			if(no.getFB() == 1){
				no.setFB(-1);
				aux.setFB(-1);
			}else if(no.getFB() == 0){
				no.setFB(-1);
				aux.setFB(0);
			}else{
				no.setFB(-2);
				aux.setFB(0);
			}
		}
		return no;
	}

	//-------------------------------//
	public void atualizarRemoverRN(No no){

		if(no.isRubro()){//caso que tira um rubro
		}
		else if( no.getFilhoDir() != null && no.getFilhoEsq() != null && no.isNegro() && no.getFilhoDir().isRubro() && no.getFilhoEsq().isRubro()){
			no.getFilhoEsq().setCor("Negro");//caso que tira um negro com dois filhos rubro 

		}else if(no.getFilhoEsq() == null && no.isNegro() && no.getFilhoDir().isRubro() && no.getFilhoEsq().isRubro()){
			no.getFilhoDir().setCor("Negro");//caso que tira um negro com um filho rubro direito
		}else if(no.isNegro() && no.getIrmao().isRubro() && no.getPai().isNegro()){//no para remover negro, irmão rubro e pai negro
			no.setDuploNegro(1);
			no.getIrmao().setCor("Negro");
			no.getPai().setCor("Rubro");
			rotacaoSE(no, 1);


		}else if( no.getFilhoDir() != null && no.getFilhoEsq() != null && no.isNegro() && no.getFilhoDir().isNegro() && no.getFilhoEsq().isNegro()){//todos negros
			no.getIrmao().setCor("Rubro");
		}else if(no.getIrmao() !=null && no.getIrmao().hasFilhoDir() && no.getIrmao().hasFilhoEsq() && no.getPai().isRubro() && no.isNegro() && no.getIrmao().isNegro() && no.getSobrinhoDir().isNegro() && no.getSobrinhoEsq().isNegro()){
			no.getIrmao().setCor("Rubro");
			no.getPai().setCor("Negro");
		}
	}
}