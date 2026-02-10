package insurancemanagement.response.sales;

import java.util.List;

import insurancemanagement.vo.sales.SalesVO;

public class SalesResponseObject {

	private String successMessage;
	private String failureMessage;

	private SalesVO salesVO;
	private List<SalesVO> salesList;

	// -------- Getter & Setter for SalesVO --------

	public SalesVO getSalesVO() {
		return salesVO;
	}

	public void setSalesVO(SalesVO salesVO) {
		this.salesVO = salesVO;
	}

	// -------- Getter & Setter for Sales List --------

	public List<SalesVO> getSalesList() {
		return salesList;
	}

	public void setSalesList(List<SalesVO> salesList) {
		this.salesList = salesList;
	}

	// -------- Getter & Setter for Messages --------

	public String getSuccessMessage() {
		return successMessage;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}

	public String getFailureMessage() {
		return failureMessage;
	}

	public void setFailureMessage(String failureMessage) {
		this.failureMessage = failureMessage;
	}
}
