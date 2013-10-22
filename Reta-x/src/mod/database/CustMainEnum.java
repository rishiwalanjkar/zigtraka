package mod.database;

public enum CustMainEnum {
	CustMain("cust_main","cust_name","cust_area","cust_address","cust_email","cust_mobile");
	
	 public String TableName, Name,Area,Address, Email, Mobile;
	 
	 private CustMainEnum(String TableName,String Name,String Area,String Address,String Email,String Mobile){
		 this.TableName=TableName;
		 this.Name=Name;
		 this.Area=Area;
		 this.Address=Address;
		 this.Email=Email;
		 this.Mobile=Mobile;
	 }
	 
}
