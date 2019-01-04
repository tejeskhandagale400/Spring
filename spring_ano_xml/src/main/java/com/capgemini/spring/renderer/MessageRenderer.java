package com.capgemini.spring.renderer;

import com.capgemini.spring.provider.MessageProvider;

public interface MessageRenderer {

	void setProvider(MessageProvider provider);

	void render();

}