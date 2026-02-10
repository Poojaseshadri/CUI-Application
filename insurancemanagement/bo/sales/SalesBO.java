package insurancemanagement.bo.sales;

import java.util.List;

import insurancemanagement.dao.sales.ISalesDAO;
import insurancemanagement.dao.sales.SalesDAO;
import insurancemanagement.exception.sales.RecordNotFoundException;
import insurancemanagement.exception.sales.SalesManagementException;
import insurancemanagement.vo.sales.SalesVO;

public class SalesBO {

    ISalesDAO salesdao = new SalesDAO();

    // ADD SALES 
    public boolean addSales(SalesVO salesVO) throws SalesManagementException {

        boolean flag = false;

        if (salesVO == null) {
            throw new SalesManagementException("Sales details cannot be null");
        }

        if (salesVO.getSalesId() == null ||
            salesVO.getAgentId() == null ||
            salesVO.getCustomerId() == null ||
            salesVO.getSalesDate() == null) {

            throw new SalesManagementException("Mandatory sales fields missing");
        }

        flag = salesdao.saveSales(salesVO);
        return flag;
    }

    //FETCH BY ID 
    public SalesVO fetchSalesById(String salesId)
            throws RecordNotFoundException {

        if (salesId == null) {
            throw new RecordNotFoundException("Sales ID cannot be null");
        }

        SalesVO salesVO = salesdao.fetchSalesById(salesId);

        if (salesVO == null) {
            throw new RecordNotFoundException("Sales record not found");
        }

        return salesVO;
    }

    //FETCH ALL
    public List<SalesVO> fetchAllSales() throws RecordNotFoundException {

        List<SalesVO> list = salesdao.fetchAllSales();

        if (list == null || list.isEmpty()) {
            throw new RecordNotFoundException("No sales records found");
        }

        return list;
    }

    //UPDATE
    public boolean updateSales(SalesVO salesVO)
            throws SalesManagementException {

        if (salesVO == null || salesVO.getSalesId() == null) {
            throw new SalesManagementException("Invalid sales data");
        }

        return salesdao.updateSales(salesVO);
    }

    //DELETE
    public boolean deleteSales(String salesId)
            throws SalesManagementException {

        if (salesId == null) {
            throw new SalesManagementException("Sales ID cannot be null");
        }

        return salesdao.deleteSales(salesId);
    }
}
