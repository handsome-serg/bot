package com.handsome.bittrex.api;

import com.handsome.repository.Market;

import java.util.List;

/*
This is wrapper class to handle response from Bittrix API
API documentation https://bittrex.github.io/api/v1-1

Request Url Example

        https://api.bittrex.com/api/v1.1/public/getmarkets

Response Example (200 OK)

{
  "success": true,
  "message": "''",
  "result": [
    {
      "MarketCurrency": "LTC",
      "BaseCurrency": "BTC",
      "MarketCurrencyLong": "Litecoin",
      "BaseCurrencyLong": "Bitcoin",
      "MinTradeSize": 0.01,
      "MarketName": "BTC-LTC",
      "IsActive": true,
      "IsRestricted": false,
      "Created": "2014-02-13T00:00:00",
      "Notice": "BTC-LTC",
      "IsSponsored": false,
      "LogoUrl": "https://storage.blob.core.windows.net/public/8637ccad-9e7f-45ac-8f03-a41b440e3911.png"
    }
  ]
}

*/

public class ResponseGetMarkets {

    public boolean success;

    public String message;

    public List<Market> result;

}
