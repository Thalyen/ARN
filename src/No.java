@SuppressWarnings("unused")
public class No
{
	private int chave;
	private No pai;
	private No filhoEsq;
	private No filhoDir;
	private No sobrinhoEsq;
	private No sobrinhoDir;
	private No tio;
	private No irmao;
	private No avo;
	private int FB;
	private String cor;
	private int duploNegro;

	public No(int chave){
		this.chave = chave;
	}

	public No(No pai, int chave){
		this.pai = pai;
		this.chave = chave;
	}

	public No(int chave, int fb){
		this.chave = chave;
		this.FB = fb;
	}

	public void setChave(int chave){
		this.chave = chave;
	}

	public int getChave(){
		return chave;
	}

	public void setPai(No pai){
		this.pai = pai;
	}

	public No getPai(){
		return pai;
	}

	public No getFilhoEsq(){
		return filhoEsq;
	}

	public void setFilhoEsq(No filhoEsq){
		this.filhoEsq = filhoEsq;
	}

	public No getFilhoDir(){
		return filhoDir;
	}

	public void setFilhoDir(No filhoDir){
		this.filhoDir = filhoDir;
	}

	public boolean hasFilhoDir(){
		return (getFilhoDir() != null);
	}

	public boolean hasFilhoEsq(){
		return (getFilhoEsq() != null);
	}

	public int getFB() {
		return FB;
	}

	public void setFB(int fB) {
		FB = fB;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public boolean isRubro(){
		if(this.getCor() == "Rubro")
			return true;
		else
			return false;
	}

	public boolean isNegro(){
		if(this.getCor() == "Negro")
			return true;
		else
			return false;
	}

	public int getDuploNegro() {
		return duploNegro;
	}

	public void setDuploNegro(int duploNegro) {
		this.duploNegro = duploNegro;
	}

	public No getSobrinhoEsq() {
		return irmao.getFilhoEsq();
	}

	public void setSobrinhoEsq(No sobrinhoEsq) {
		this.sobrinhoEsq = sobrinhoEsq;
	}

	public No getSobrinhoDir() {
		return irmao.getFilhoDir();
	}

	public void setSobrinhoDir(No sobrinhoDir) {
		this.sobrinhoDir = sobrinhoDir;
	}

	public No getAvo() {
		return pai.getPai();
	}

	public void setAvo(No avo) {
		this.avo = getPai().getPai();
	}

	public No getTio() {
		if(hasTio()){
			if(getPai().getPai().getFilhoDir() == getPai())
				return getPai().getPai().getFilhoEsq();
			else if(getPai().getPai().getFilhoEsq() == getPai())
				return getPai().getPai().getFilhoDir();
		}
		System.out.println("Sem tio1");
		return null;
	}

	public boolean hasTio(){
		if(pai.getIrmao() != null)
			return true;
		else
			return false;
	}

	public void setTio(No tio) {
		this.tio = tio;
	}

	public No getIrmao() {
		if(pai.getFilhoDir() == this && pai.hasFilhoEsq())
			return pai.getFilhoEsq();
		else if(pai.getFilhoEsq() == this && pai.hasFilhoDir())
			return pai.getFilhoDir();
		else return null;
	}

	public void setIrmão(No irmão) {
		this.irmao = irmão;
	}


}