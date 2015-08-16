package net.sorax.engine.network.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;

import net.sorax.engine.network.packet.Packet;

public abstract class SCEServer extends Thread {
	
	private DatagramSocket socket;
	private Map<Integer, Packet> packets;
	
	public SCEServer(int port) {
		this.packets = new HashMap<Integer, Packet>();
		try {
			this.socket = new DatagramSocket(port);
		} catch (SocketException e) {
			e.printStackTrace();
		}
		this.init();
	}
	
	protected abstract void init();
	
	public void run() {
		while(true) {
			byte[] data = new byte[1024];
			DatagramPacket packet = new DatagramPacket(data, data.length);
			try {
				socket.receive(packet);
			} catch (IOException e) {
				e.printStackTrace();
			}
			this.parsePacket(packet.getData(), packet.getAddress(), packet.getPort());
		}
	}
	
	private void parsePacket(byte[] data, InetAddress address, int port) {
		String message = new String(data).trim();
		int packetId = Integer.parseInt(message.substring(0, 2));
		Packet packet = this.packets.get(packetId);
		packet.setData(data);
		packet.handleServer(this);
	}

	protected void addPacket(Packet packet) {
		this.packets.put((int) packet.getId(), packet);
	}
	
	public void sendData(byte[] data, InetAddress ipAdress, int port) {
		DatagramPacket packet = new DatagramPacket(data, data.length, ipAdress, port);
		try {
			this.socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
