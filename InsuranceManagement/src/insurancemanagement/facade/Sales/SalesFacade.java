package insurancemanagement.facade.Sales;

import java.util.List;

import insurancemanagement.bo.sales.SalesBO;
import insurancemanagement.exception.sales.RecordNotFoundException;
import insurancemanagement.exception.sales.SalesManagementException;
import insurancemanagement.vo.sales.SalesVO;

public class SalesFacade {

	SalesBO salesbo = new SalesBO();

	//ADD SALES
	public boolean addSales(SalesVO salesVO) throws SalesManagementException {

		boolean flag = false;
		flag = salesbo.addSales(salesVO);
		return flag;
	}

	//FETCH BY ID
	public SalesVO fetchSalesById(String salesId) throws RecordNotFoundException {

		SalesVO salesVO = new SalesVO();
		salesVO = salesbo.fetchSalesById(salesId);
		return salesVO;
	}

	//UPDATE
	public boolean updateSales(SalesVO salesVO) throws SalesManagementException {

		boolean flag;
		flag = salesbo.updateSales(salesVO);
		return flag;
	}

	//FETCH ALL 
	public List<SalesVO> fetchAllSales() throws RecordNotFoundException {

		List<SalesVO> list;
		list = salesbo.fetchAllSales();
		return list;
	}

	//DELETE
	public boolean deleteSales(String salesId) throws SalesManagementException {

		boolean flag;
		flag = salesbo.deleteSales(salesId);
		return flag;
	}

	public SalesVO fetchSalesById1(String salesId) {
		return null;
	}
}
