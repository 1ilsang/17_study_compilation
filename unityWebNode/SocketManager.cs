using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using SocketIOClient;

public class SocketManager : MonoBehaviour {

    string url = "http://127.0.0.1:999/";
    public static Client Socket { get; private set; }
	// Use this for initialization
	void Awake () {
        Socket = new Client(url);
        Socket.Opened += SocketOpened;
        Socket.Connect();
	}
	
    private void SocketOpened(object sender, System.EventArgs e)
    {
        Debug.Log("Unity Socket Opened");
    }

	// Update is called once per frame
	void OnDisable () {
        Socket.Close();
	}
}
