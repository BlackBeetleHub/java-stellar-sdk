package org.stellar.sdk.requests;

import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Request;
import org.stellar.sdk.Asset;
import org.stellar.sdk.AssetTypeCreditAlphaNum;
import org.stellar.sdk.responses.OrderBookResponse;

import java.io.IOException;
import java.net.URI;

/**
 * Builds requests connected to order book.
 */
public class OrderBookRequestBuilder extends RequestBuilder {
  public OrderBookRequestBuilder(URI serverURI) {
    super(serverURI, "order_book");
  }

  public OrderBookRequestBuilder buyingAsset(Asset asset) {
    uriBuilder.addParameter("buying_asset_type", asset.getType());
    if (asset instanceof AssetTypeCreditAlphaNum) {
      AssetTypeCreditAlphaNum creditAlphaNumAsset = (AssetTypeCreditAlphaNum) asset;
      uriBuilder.addParameter("buying_asset_code", creditAlphaNumAsset.getCode());
      uriBuilder.addParameter("buying_asset_issuer", creditAlphaNumAsset.getIssuer().getAccountId());
    }
    return this;
  }
  
  public OrderBookRequestBuilder sellingAsset(Asset asset) {
    uriBuilder.addParameter("selling_asset_type", asset.getType());
    if (asset instanceof AssetTypeCreditAlphaNum) {
      AssetTypeCreditAlphaNum creditAlphaNumAsset = (AssetTypeCreditAlphaNum) asset;
      uriBuilder.addParameter("selling_asset_code", creditAlphaNumAsset.getCode());
      uriBuilder.addParameter("selling_asset_issuer", creditAlphaNumAsset.getIssuer().getAccountId());
    }
    return this;
  }

  public static OrderBookResponse execute(URI uri) throws IOException, TooManyRequestsException {
    TypeToken type = new TypeToken<OrderBookResponse>() {};
    ResponseHandler<OrderBookResponse> responseHandler = new ResponseHandler<OrderBookResponse>(type);
    return (OrderBookResponse) Request.Get(uri).execute().handleResponse(responseHandler);
  }

  public OrderBookResponse execute() throws IOException, TooManyRequestsException {
    return this.execute(this.buildUri());
  }

  @Override
  public RequestBuilder cursor(String cursor) {
    throw new RuntimeException("Not implemented yet.");
  }

  @Override
  public RequestBuilder limit(int number) {
    throw new RuntimeException("Not implemented yet.");
  }

  @Override
  public RequestBuilder order(Order direction) {
    throw new RuntimeException("Not implemented yet.");
  }
}
