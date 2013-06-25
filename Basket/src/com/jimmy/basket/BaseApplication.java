package com.jimmy.basket;

import android.app.Application;

public class BaseApplication extends Application{

	BasketEntity basketEntity = new BasketEntity();

	public BasketEntity getBasketEntity() {
		return basketEntity;
	}
	
}
