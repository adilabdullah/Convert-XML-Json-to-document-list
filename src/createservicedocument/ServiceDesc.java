package createservicedocument;
public class ServiceDesc {

	String fieldName,sampleValue,length,type,desc,isMand;

	@Override
	public String toString() {
		return "ServiceDesc [fieldName=" + fieldName + ", sampleValue="
				+ sampleValue + ", length=" + length + ", type=" + type
				+ ", desc=" + desc + ", isMand=" + isMand + "]";
	}

	public ServiceDesc() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ServiceDesc(String fieldName, String sampleValue, String length,
			String type, String desc, String isMand) {
		super();
		this.fieldName = fieldName;
		this.sampleValue = sampleValue;
		this.length = length;
		this.type = type;
		this.desc = desc;
		this.isMand = isMand;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getSampleValue() {
		return sampleValue;
	}

	public void setSampleValue(String sampleValue) {
		this.sampleValue = sampleValue;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getIsMand() {
		return isMand;
	}

	public void setIsMand(String isMand) {
		this.isMand = isMand;
	}
	
	
}
