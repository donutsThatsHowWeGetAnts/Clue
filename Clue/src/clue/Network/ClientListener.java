/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clue.Network;

/**
 *
 * @author glender
 */
public interface ClientListener {

	/**
	 *
	 */
	public void unknownHost();

	/**
	 *
	 */
	public void couldNotConnect();

	/**
	 *
	 * @param msg
	 */
	public void recivedInput(String msg);

	/**
	 *
	 */
	public void serverClosed();

	/**
	 *
	 */
	public void disconnected();

	/**
	 *
	 */
	public void connectedToServer();

}
