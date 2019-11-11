package com.handsome.bittrex.api;

import com.handsome.repository.MarketSummary;

import java.util.List;

/*
This is wrapper class to handle response from Bittrix API
API documentation https://bittrex.github.io/api/v1-1

Request Url Example

        https://api.bittrex.com/api/v1.1/public/getmarketsummaries

Response Example (200 OK)

{
  "success": true,
  "message": "''",
  "result": [
    {
      "MarketName": "BTC-LTC",
      "High": 0.0135,
      "Low": 0.012,
      "Volume": 3833.97619253,
      "Last": 0.01349998,
      "BaseVolume": 47.03987026,
      "TimeStamp": "2014-07-09T07:22:16.72",
      "Bid": 0.01271001,
      "Ask": 0.012911,
      "OpenBuyOrders": 45,
      "OpenSellOrders": 45,
      "PrevDay": 0.01229501,
      "Created": "2014-02-13T00:00:00",
      "DisplayMarketName": "string"
    }
  ]
}
*/

public class ResponseGetSummaries {

    public boolean success;

    public String message;

    public List<MarketSummary> result;

}
