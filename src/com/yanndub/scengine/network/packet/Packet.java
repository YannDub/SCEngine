package com.yanndub.scengine.network.packet;

import com.yanndub.scengine.network.client.SCEClient;
import com.yanndub.scengine.network.server.SCEServer;

public abstract class Packet {
	
	private byte id;
	protected byte[] data;
	
	public Packet(int id) {
		this.id = (byte)id;
	}
	
	public byte getId() {
		return this.id;
	}
	
	public abstract void writeData(SCEServer server);

	public abstract void writeData(SCEClient client);
	
	public void setData(byte[] data) {
		this.data = data;
	}
	
	protected String readData(byte[] data) {
		String message = new String(data).trim();
		return message.substring(2);
	}
	
	public abstract void handleClient(SCEClient client);
	
	public abstract void handleServer(SCEServer server);
	
	public abstract byte getData();
}
