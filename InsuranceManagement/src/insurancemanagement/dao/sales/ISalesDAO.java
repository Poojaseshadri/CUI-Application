package insurancemanagement.dao.sales;

import java.util.List;

import insurancemanagement.exception.sales.RecordNotFoundException;
import insurancemanagement.exception.sales.SalesManagementException;
import insurancemanagement.vo.sales.SalesVO;

public interface ISalesDAO {

	String userName = "root";
	String password = "Pooja@23";
	String url = "jdbc:mysql://localhost:3306/insurance?useSSL=false";

	public boolean saveSales(SalesVO salesVO) throws SalesManagementException;

	public SalesVO fetchSalesById(String salesId) throws RecordNotFoundException;

	public List<SalesVO> fetchAllSales() throws RecordNotFoundException;

	public boolean updateSales(SalesVO salesVO) throws SalesManagementException;

	public boolean deleteSales(String salesId) throws SalesManagementException;
}
