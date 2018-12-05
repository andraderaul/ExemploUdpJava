//processo 1
package br.ufs.dcomp.ExemploUdpJava;

import java.net.*;
import java.util.*;

public class AppUDP1 {

    public static void main(String[] args) throws SocketException {
        try{
            System.out.print("[ Alocando porta UDP      ..................  ");
    	    DatagramSocket socket = new DatagramSocket(10000); //datagrama socket alocado na porta 10000
            System.out.println("[OK] ]");
            
            
            int i = 0;
            
            while(i < 10) {
                //String msg = "Olá!!!";
                Scanner se = new Scanner(System.in);
                String msg = se.nextLine();
                
                byte[] msg_buf = msg.getBytes(); //transforma a msg em um vetor de bytes
                int msg_size = msg_buf.length; // pega o tamanho do vetor
                InetAddress destination_address = InetAddress.getLocalHost(); //consulta o dns e o ip de origem
                int destination_port = 20000; 
    
                System.out.print("[ Montando datagrama UDP  ..................  ");
                DatagramPacket pack = new DatagramPacket(msg_buf, msg_size, destination_address, destination_port); // aqui monta o datagrama
                System.out.println("[OK] ]");
                
                System.out.print("[ Enviando datagrama UDP  ..................  ");
                socket.send(pack); //aqui envia o pacote pelo soket alocado la em cima, entrega o datagrama para o sistema operacional
                System.out.println("[OK] ]");
                
                
                //parte 2 ------------- MINHA ALTERACAO -------------
                /*aqui cria um pacote vazio para ser recebido no receive*/
                byte[] buf = new byte[20];
                pack = new DatagramPacket(buf, buf.length); //aqui é o pacote criado
    
                System.out.print("[ Aguardando recebimento de mensagem  ..................  ");
                socket.receive(pack); //chamando a operacao receive passando o pacote vazio e essa acao é bloqueante
                System.out.println("[OK] ]");
                
                byte[] received_data = pack.getData(); //aqui extrai o vetor de byte
                String received_msg = new String(received_data); // aqui converte o vetor de byte em string
                InetAddress origin_address = pack.getAddress(); //pega o ip de origem
                int origin_port = pack.getPort(); // pega a porta de origem
                
                System.out.println("  Mensagem:             "+received_msg);
                System.out.println("  Endereço de origem:   "+origin_address.getHostAddress());
                System.out.println("  Porta de origem:      "+origin_port);
            
                i++;
            }
            
        } catch (Exception e){
            System.out.println(e.getMessage());
        }    
    }
}