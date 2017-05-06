/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clue.Network;

import java.io.PrintWriter;

/**
 *
 * @author glender
 */
public interface ServerListener {

	/**
	 *
	 * @param client
	 * @param out
	 */
	public void clientConncted(ClientInstance client, PrintWriter out);

	/**
	 *
	 * @param client
	 */
	public void clientDisconnected(ClientInstance client);

	/**
	 *
	 * @param client
	 * @param msg
	 */
	public void recivedInput(ClientInstance client, String msg);

	/**
	 *
	 */
	public void serverClosed();

}
