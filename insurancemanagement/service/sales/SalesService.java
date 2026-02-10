package insurancemanagement.service.sales;

import java.util.List;

import org.apache.log4j.Logger;

import insurancemanagement.exception.sales.RecordNotFoundException;
import insurancemanagement.exception.sales.SalesManagementException;
import insurancemanagement.facade.Sales.SalesFacade;
import insurancemanagement.response.sales.SalesResponseObject;
import insurancemanagement.vo.sales.SalesVO;

public class SalesService {

	SalesFacade facade = new SalesFacade();
	SalesResponseObject obj = new SalesResponseObject();
	Logger log = Logger.getLogger(SalesService.class.getName());

	//ADD SALES
	public SalesResponseObject addSales(SalesVO salesVO) {

		boolean flag;

		try {
			log.info("Adding sales with SalesId : " + salesVO.getSalesId());

			flag = facade.addSales(salesVO);

			if (flag) {
				log.info("Sales added successfully. SalesId : " + salesVO.getSalesId());
				obj.setSuccessMessage("Sales Added Successfully");
			} else {
				log.warn("Sales addition failed. SalesId : " + salesVO.getSalesId());
				obj.setFailureMessage("Sales Not Added");
			}

		} catch (SalesManagementException e) {
			log.error("Error occurred while adding sales. SalesId : " + salesVO.getSalesId(), e);
			obj.setFailureMessage(e.getMessage());
		}

		return obj;
	}

	//FETCH BY ID
	public SalesResponseObject fetchSalesById(String salesId) {

		SalesVO salesVO = null;

		try {
			log.info("Fetching sales details for SalesId : " + salesId);

			salesVO = facade.fetchSalesById(salesId);

			if (salesVO != null && salesVO.getSalesId() != null) {
				log.info("Sales details retrieved successfully. SalesId : " + salesId);
				obj.setSalesVO(salesVO);
			} else {
				log.warn("No sales record found for SalesId : " + salesId);
				obj.setFailureMessage("Sales not found");
			}

		} catch (RecordNotFoundException e) {
			log.error("Error occurred while fetching sales. SalesId : " + salesId, e);
			obj.setFailureMessage(e.getMessage());
		}

		return obj;
	}

	//FETCH ALL
	public SalesResponseObject fetchAllSales() {

		try {
			log.info("Fetching all sales records");

			List<SalesVO> list = facade.fetchAllSales();

			log.info("All sales records retrieved successfully");
			obj.setSalesList(list);

		} catch (RecordNotFoundException e) {
			log.error("Error occurred while fetching all sales records", e);
			obj.setFailureMessage(e.getMessage());
		}

		return obj;
	}

	//UPDATE
	public SalesResponseObject updateSales(SalesVO vo) {

		try {
			log.info("Updating sales details for SalesId : " + vo.getSalesId());

			boolean flag = facade.updateSales(vo);

			if (flag) {
				log.info("Sales updated successfully. SalesId : " + vo.getSalesId());
				obj.setSuccessMessage("Sales Updated Successfully");
			} else {
				log.warn("Sales update failed. SalesId : " + vo.getSalesId());
				obj.setFailureMessage("Sales Not Updated");
			}

		} catch (SalesManagementException e) {
			log.error("Error occurred while updating sales. SalesId : " + vo.getSalesId(), e);
			obj.setFailureMessage(e.getMessage());
		}

		return obj;
	}

	//DELETE
	public SalesResponseObject deleteSales(String salesId) {

		try {
			log.info("Deleting sales record with SalesId : " + salesId);

			boolean flag = facade.deleteSales(salesId);

			if (flag) {
				log.info("Sales deleted successfully. SalesId : " + salesId);
				obj.setSuccessMessage("Sales Deleted Successfully");
			} else {
				log.warn("Sales deletion failed. SalesId : " + salesId);
				obj.setFailureMessage("Sales Not Deleted");
			}

		} catch (SalesManagementException e) {
			log.error("Error occurred while deleting sales. SalesId : " + salesId, e);
			obj.setFailureMessage(e.getMessage());
		}

		return obj;
	}
}
