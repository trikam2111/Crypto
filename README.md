# Crypto REST API
REST API for checking currencies exchange rates (includes crypto).\
Tech stack:
* Java 8
* Spring Framework
* JUnit
* Gradle
##ENDPOINTS
###Rates
```
/currencies/{currency}?filter=[]
```
Returns exchanges rate for given currency. If filter is applied, shows only rates for chosen values.
###Example response
```
/currencies/BTC?filter=USD,ETH
{
    "USD": 42728.514895554596,
    "ETH": 13.218637264330043,
    "source": "BTC"
}
```
###Exchange
```
/currencies/exchange
```
For given currencies and amount returns exchange result value including 1% fee.
###Example response
```
/currencies/exchange
Body:
 {
     "from":"BTC",
     "to":["USD", "ETH"],
     "amount":121
 }
 Response:
{
    "USD": {
        "name": "USD",
        "rate": 42734.09613863601,
        "amount": 121.0,
        "result": 5119117.376447207
    },
    "ETH": {
        "name": "ETH",
        "rate": 13.21213902564118,
        "amount": 121.0,
        "result": 1582.682133881557
    },
    "from": "BTC"
}
```
