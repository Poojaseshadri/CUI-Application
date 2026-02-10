# CUI-Application
*Agent / Advisor Management*
*Features*
•	Register & Manage Agents
•	Agent Sells Policy to Customer
•	Agent Commission Calculation
•	View Commission Statements
•	Track Policies Sold by Agent
•	Update Agent Profile

*Business Logic*
1. Commission Calculation
Commission = CollectedPremium × CommissionRate
CommissionRate depends on:
•	Policy type (Life / Health / Motor)
•	Policy tenure
•	Add-ons or riders selected
•	New purchase vs renewal

2. Commission Statement
Agents can view:
•	Total commission earned
•	Month-wise breakdown
•	Policy-wise commission list
•	Pending payouts
•	Downloadable commission reports

3.  Agent Sells Policy to Customer

•	When the agent enters a sale, a record is inserted into Sales table
•	The PolicyID is updated with AgentID
•	Commission calculation is triggered automatically based on the premium
•	Validation:
o	Agent must be active
o	CustomerID must exist
o	PolicyID must not already be linked to another agent.

