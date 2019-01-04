package com.capgemini.spring.renderer;

import com.capgemini.spring.provider.MessageProvider;

public class PDFMessageRenderer implements MessageRenderer {
	private MessageProvider provider;
	
	public PDFMessageRenderer(MessageProvider provider) {
		this.provider = provider;
	}
	public PDFMessageRenderer() {
	}

	public void setProvider(MessageProvider provider) {
		this.provider = provider;
	}

	public void render() {
		System.out.println("THIS IS IN PDF = "+provider.getMessage());
	}
}
