package crosser
Defines an application in Crosser.java that
 - generates 30 random buy and sell orders according to the parameters given
 - generates and prints out price and size of crossing trade
 - includes a JUnit-based set of unit tests 

Notes:
	- all parameters are hard-coded into application, but could easily be made configurable.
 	- in the (unlikely) event of a negative price or size being generated, it is rounded up to $0.0001 or 1 share respectively.
 	- the order book assumes prices have resolution of $0.0001 and stores as integers based on this minimum tick size
	- if no cross is possible, a trade with price=0.0 and size=0 is returned
	- the set of unit tests is intended to be useful but not exhaustive
	- this is my first program in Java, so please excuse any linguistic infelicities


