package insurancemanagement.main.sales;

import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import insurancemanagement.response.sales.SalesResponseObject;
import insurancemanagement.service.sales.SalesService;
import insurancemanagement.vo.sales.SalesVO;

public class SalesMain {

	static Logger log = Logger.getLogger(SalesMain.class.getName());

	public static void main(String[] args) {

		PropertyConfigurator.configure(SalesMain.class.getClassLoader().getResource("log4j.properties"));

		log.info("Sales Management System Application Started .....");
		int menuSelected;
		do {
		System.out.println("1. Add Sales");
		System.out.println("2. Fetch Sales by Sales ID");
		System.out.println("3. Fetch All Sales");
		System.out.println("4. Update Sales Details");
		System.out.println("5. Delete Sales");
		System.out.println("6. Exit");
		System.out.println("Enter Your Choice:");
		
		
		Scanner s = new Scanner(System.in);
		menuSelected = s.nextInt();
		s.nextLine();

		switch (menuSelected) {

		case 1:
			addSales();
			break;

		case 2:
			fetchSales();
			break;

		case 3:
			fetchAllSales();
			break;

		case 4:
			updateSales();
			break;

		case 5:
			deleteSales();
			break;

		case 6:
			System.out.println("Exiting Sales Management System...");
			log.info("Application exited by user");
			System.exit(0);

		default:
			System.out.println("Invalid Option");
		}
	}while(menuSelected != 6);
	}
	//ADD SALES
	private static void addSales() {

		SalesService salesService = new SalesService();
		Scanner s = new Scanner(System.in);

		try {
			System.out.println("Please Enter Sales ID:");
			String salesId = s.nextLine();

			System.out.println("Please Enter Agent ID:");
			String agentId = s.nextLine();

			System.out.println("Please Enter Customer ID:");
			String customerId = s.nextLine();

			System.out.println("Please Enter Policy ID:");
			int policyId = Integer.parseInt(s.nextLine());

			System.out.println("Please Enter Sales Date (yyyy-mm-dd):");
			java.util.Date salesDate = java.sql.Date.valueOf(s.nextLine());

			SalesVO salesvo = new SalesVO();
			salesvo.setSalesId(salesId);
			salesvo.setAgentId(agentId);
			salesvo.setCustomerId(customerId);
			salesvo.setPolicyId(policyId);
			salesvo.setSalesDate(salesDate);

			SalesResponseObject obj = salesService.addSales(salesvo);

			if (obj.getSuccessMessage() != null) {
				System.out.println(obj.getSuccessMessage());
			} else {
				System.out.println(obj.getFailureMessage());
			}

		} catch (Exception e) {
			System.out.println("Invalid Input! Please check values.");
			log.error("Error while adding sales", e);
		}
	}

	//FETCH SALES
	private static void fetchSales() {

		Scanner s = new Scanner(System.in);
		System.out.println("Please Enter The Sales ID To Be Fetched:");
		String salesId = s.nextLine();

		SalesService salesService = new SalesService();
		SalesResponseObject obj = salesService.fetchSalesById(salesId);
		SalesVO salesvo = obj.getSalesVO();

		if (salesvo != null && salesvo.getSalesId() != null) {

			System.out.println("------------------------------------------------------------------");
			System.out.println("SalesId\tAgentId\tCustomerId\tPolicyId\t\tSalesDate");
			System.out.println("------------------------------------------------------------------");

			System.out.println(salesvo.getSalesId() + "\t" + salesvo.getAgentId() + "\t" + salesvo.getCustomerId()
					+ "\t\t" + salesvo.getPolicyId() + "\t\t" + salesvo.getSalesDate());

		} else {
			System.out.println(obj.getFailureMessage());
		}
	}

	//FETCH ALL SALES
	private static void fetchAllSales() {

		SalesService salesService = new SalesService();
		SalesResponseObject obj = salesService.fetchAllSales();
		List<SalesVO> list = obj.getSalesList();

		System.out.println("-------------------------------------------------------------------------------------------");
		System.out.println("SalesId\tAgentId\tCustomerId\tPolicyId\tSalesDate\tCreatedDate\tUpdatedDate");
		System.out.println("-------------------------------------------------------------------------------------------");

		if (list != null && !list.isEmpty()) {

			for (SalesVO salesvo : list) {
				System.out.println(salesvo.getSalesId() + "\t" + salesvo.getAgentId() + "\t" + salesvo.getCustomerId()
						+ "\t\t" + salesvo.getPolicyId() + "\t" + salesvo.getSalesDate() + "\t"
						+ salesvo.getCreatedDate() + "\t" + salesvo.getUpdatedDate());
			}

		} else {
			System.out.println(obj.getFailureMessage());
		}
	}

	//UPDATE SALES

	private static void updateSales() {

		SalesService salesService = new SalesService();
		Scanner s = new Scanner(System.in);

		try {
			System.out.println("Please Enter Sales ID To Be Updated:");
			String salesId = s.nextLine();

			System.out.println("Please Enter New Agent ID:");
			String agentId = s.nextLine();

			System.out.println("Please Enter New Customer ID:");
			String customerId = s.nextLine();

			System.out.println("Please Enter New Policy ID:");
			int policyId = Integer.parseInt(s.nextLine());

			System.out.println("Please Enter New Sales Date (yyyy-mm-dd):");
			java.util.Date salesDate = java.sql.Date.valueOf(s.nextLine());

			SalesVO salesvo = new SalesVO();
			salesvo.setSalesId(salesId);
			salesvo.setAgentId(agentId);
			salesvo.setCustomerId(customerId);
			salesvo.setPolicyId(policyId);
			salesvo.setSalesDate(salesDate);

			SalesResponseObject obj = salesService.updateSales(salesvo);

			if (obj.getSuccessMessage() != null) {
				System.out.println(obj.getSuccessMessage());
			} else {
				System.out.println(obj.getFailureMessage());
			}

		} catch (Exception e) {
			System.out.println("Invalid Input");
			log.error("Update sales error", e);
		}
	}

	//DELETE SALES
	private static void deleteSales() {

		SalesService salesService = new SalesService();
		Scanner s = new Scanner(System.in);

		try {
			System.out.println("Please Enter The Sales ID To Be Deleted:");
			String salesId = s.nextLine();

			SalesResponseObject obj = salesService.deleteSales(salesId);

			if (obj.getSuccessMessage() != null) {
				System.out.println(obj.getSuccessMessage());
			} else {
				System.out.println(obj.getFailureMessage());
			}

		} catch (Exception e) {
			System.out.println("Invalid Sales ID");
			log.error("Delete sales input error", e);
		}
	}
}
