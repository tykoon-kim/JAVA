package model.rec;

public class OrderVO {
	int mocode, memcode, paynum, shopcode, amscode, amsquantity;
	String diname, method, machinecycle, management, image, name, modate, mototalprice, modeliver, mototal,
			shopquantity, amsnation, amscom, amssignificant, amsprice, acname, acwarranty, acexp;

	public OrderVO() {
		// TODO Auto-generated constructor stub
	}

	// am_seller
	public OrderVO(int amscode, String amsnation, String amscom, String amsprice, int amsquantity,
			String amssignificant) {
		this.amscode = amscode;
		this.amsnation = amsnation;
		this.amscom = amscom;
		this.amsprice = amsprice;
		this.amsquantity = amsquantity;
		this.amssignificant = amssignificant;
	}

	// machineorder
	public OrderVO(int mocode, String modate, String mototalprice, String modeliver, int memcode, int paynum,
			String mototal) {
		this.mocode = mocode;
		this.modate = modate;
		this.mototalprice = mototalprice;
		this.modeliver = modeliver;
		this.memcode = memcode;
		this.paynum = paynum;
		this.mototal = mototal;
	}

	// shoppinglist
	public OrderVO(String shopquantity, int amscode, int mocode) {
		this.shopquantity = shopquantity;
		this.amscode = amscode;
		this.mocode = mocode;
	}

	public String getAcname() {
		return acname;
	}

	public void setAcname(String acname) {
		this.acname = acname;
	}

	public String getAcwarranty() {
		return acwarranty;
	}

	public void setAcwarranty(String acwarranty) {
		this.acwarranty = acwarranty;
	}

	public String getAcexp() {
		return acexp;
	}

	public void setAcexp(String acexp) {
		this.acexp = acexp;
	}

	public int getMocode() {
		return mocode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDiname() {
		return diname;
	}

	public void setDiname(String diname) {
		this.diname = diname;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getMachinecycle() {
		return machinecycle;
	}

	public void setMachinecycle(String machinecycle) {
		this.machinecycle = machinecycle;
	}

	public String getManagement() {
		return management;
	}

	public void setManagement(String management) {
		this.management = management;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void setMocode(int mocode) {
		this.mocode = mocode;
	}

	public int getMemcode() {
		return memcode;
	}

	public void setMemcode(int memcode) {
		this.memcode = memcode;
	}

	public int getPaynum() {
		return paynum;
	}

	public void setPaynum(int paynum) {
		this.paynum = paynum;
	}

	public int getShopcode() {
		return shopcode;
	}

	public void setShopcode(int shopcode) {
		this.shopcode = shopcode;
	}

	public int getAmscode() {
		return amscode;
	}

	public void setAmscode(int amscode) {
		this.amscode = amscode;
	}

	public String getModate() {
		return modate;
	}

	public void setModate(String modate) {
		this.modate = modate;
	}

	public String getMototalprice() {
		return mototalprice;
	}

	public void setMototalprice(String mototalprice) {
		this.mototalprice = mototalprice;
	}

	public String getModeliver() {
		return modeliver;
	}

	public void setModeliver(String modeliver) {
		this.modeliver = modeliver;
	}

	public String getMototal() {
		return mototal;
	}

	public void setMototal(String mototal) {
		this.mototal = mototal;
	}

	public String getShopquantity() {
		return shopquantity;
	}

	public void setShopquantity(String shopquantity) {
		this.shopquantity = shopquantity;
	}

	public int getAmsquantity() {
		return amsquantity;
	}

	public void setAmsquantity(int amsquantity) {
		this.amsquantity = amsquantity;
	}

	public String getAmsnation() {
		return amsnation;
	}

	public void setAmsnation(String amsnation) {
		this.amsnation = amsnation;
	}

	public String getAmscom() {
		return amscom;
	}

	public void setAmscom(String amscom) {
		this.amscom = amscom;
	}

	public String getAmssignificant() {
		return amssignificant;
	}

	public void setAmssignificant(String amssignificant) {
		this.amssignificant = amssignificant;
	}

	public String getAmsprice() {
		return amsprice;
	}

	public void setAmsprice(String amsprice) {
		this.amsprice = amsprice;
	}

}
