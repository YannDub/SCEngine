package net.sorax.engine.network.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import net.sorax.engine.network.packet.Packet;

public abstract class SCEClient extends Thread {
	
	private DatagramSocket socket;
	private InetAddress ipAdress;
	private int port;
	
	private Map<Integer, Packet> packets;
	
	public SCEClient(String ipAdress, int port) {
		this.port = port;
		this.packets = new HashMap<Integer, Packet>();
		try {
			this.socket = new DatagramSocket();
			this.ipAdress = InetAddress.getByName(ipAdress);
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
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
				this.socket.receive(packet);
			} catch (IOException e) {
				e.printStackTrace();
			}
			this.parsePacket(packet.getData(), packet.getAddress(), packet.getPort());
		}
	}
	
	private void parsePacket(byte[] data, InetAddress ipAdress, int port) {
		String message = new String(data).trim();
		int packetId = Integer.parseInt(message.substring(2));
		Packet packet = this.packets.get(packetId);
		packet.setData(data);
		packet.handleClient(this);
	}
	
	protected void addPacket(Packet packet) {
		this.packets.put((int) packet.getId(), packet);
	}
	
	public void sendData(byte[] data) {
		DatagramPacket packet = new DatagramPacket(data, data.length, ipAdress, port);
		try {
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
