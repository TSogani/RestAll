package com.st.resource;


public class StockTradingResourceImpl implements IStockTradingResource{
	@Override
	public float getStock(String stockName, String market) {
		float stock = 0.0f;
		if(stockName!=null && market!=null){
			if(stockName.equals("rajnigandha") && market.equals("bigbazar")){
				stock = 45.00f;
			}else if(stockName.equals("amber") && market.equals("realiance")){
				stock = 98.00f;
			}
		}
		return stock;
	}
}
