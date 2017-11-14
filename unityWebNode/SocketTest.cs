using System.Collections;
using System.Collections.Generic;
using UnityEngine.UI;
using UnityEngine;
using System;

[Serializable]
public class seqGoJson
{
    [SerializeField]
    public int seq;
}
[Serializable]
public class serverResponse
{
    [SerializeField]
    public int seq;
    public string tf;
}
[Serializable]
public class seqScoreJson
{
    [SerializeField]
    public string aScore;
    public string bScore;
    public string cScore;
    public string dScore;
    public string eScore;
    public string fScore;
    public string gScore;
    public string hScore;
    public string iScore;
    public string jScore;
    public string kScore;

}
public class SocketTest : MonoBehaviour {
    GameObject tmp, responseCube;
    private socketRet tmp2;
    private TextMesh label0, label1, label2, label3, label4, label5, label6, label7, label8, label9, label10;
    private bool responseAnswer = false;
    List<string> buffer = new List<string>();
    public float rotationSpeed = 90.0f;
    // Use this for initialization
    void Start () {
        tmp = GameObject.Find("TEMP");
        responseCube = GameObject.Find("responseEffect");
        tmp2 = tmp.GetComponent<socketRet>();
        label0 = GameObject.Find("Turret0").GetComponent<TextMesh>();
        label1 = GameObject.Find("Turret1").GetComponent<TextMesh>();
        label2 = GameObject.Find("Turret2").GetComponent<TextMesh>();
        label3 = GameObject.Find("Turret3").GetComponent<TextMesh>();
        label4 = GameObject.Find("Turret4").GetComponent<TextMesh>();
        label5 = GameObject.Find("Turret5").GetComponent<TextMesh>();
        label6 = GameObject.Find("Turret6").GetComponent<TextMesh>();
        label7 = GameObject.Find("Turret7").GetComponent<TextMesh>();
        label8 = GameObject.Find("Turret8").GetComponent<TextMesh>();
        label9 = GameObject.Find("Turret9").GetComponent<TextMesh>();
        label10 = GameObject.Find("Turret10").GetComponent<TextMesh>();

        SocketManager.Socket.On("go", (data) =>
        {
            seqGoJson d = JsonUtility.FromJson<seqGoJson>(data.Json.args[0].ToString());
           // Debug.Log(d.seq);
            tmp2.SocketRetValue = d.seq;
        });
        SocketManager.Socket.On("serverResponse", (data) =>
        {
            Debug.Log("serverResponse " + data.Json.args[0]);
            serverResponse d = JsonUtility.FromJson<serverResponse>(data.Json.args[0].ToString());
            Debug.Log("d.seq " + d.seq + "d.TF " + d.tf);
            tmp2.SocketRetTFseq = d.seq;
            tmp2.SocketRetTF = d.tf;
        });
        SocketManager.Socket.On("updateScore", (data) =>
        {
            seqScoreJson d = JsonUtility.FromJson<seqScoreJson>(data.Json.args[0].ToString());
            buffer.Add(d.aScore); //갱신된 DB를 주면 됨
            buffer.Add(d.bScore);
            buffer.Add(d.cScore);
            buffer.Add(d.dScore);
            buffer.Add(d.eScore);
            buffer.Add(d.fScore);
            buffer.Add(d.gScore);
            buffer.Add(d.hScore);
            buffer.Add(d.iScore);
            buffer.Add(d.jScore);
            buffer.Add(d.kScore);
        });
    }

    void Update()
    {
        if (buffer.Count <= 0) { return;  }
        label0.text = (buffer[0]).ToString();
        label1.text = (buffer[1]).ToString();
        label2.text = (buffer[2]).ToString();
        label3.text = (buffer[3]).ToString();
        label4.text = (buffer[4]).ToString();
        label5.text = (buffer[5]).ToString();
        label6.text = (buffer[6]).ToString();
        label7.text = (buffer[7]).ToString();
        label8.text = (buffer[8]).ToString();
        label9.text = (buffer[9]).ToString();
        label10.text = (buffer[10]).ToString();
        buffer.Clear();
    }
    
}
