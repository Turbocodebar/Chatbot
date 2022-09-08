import java.util.HashMap;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Scanner;
import java.util.Random;


// Domain (base) class with examples from general domain
class Chatbot
{
  //private String in;
  private ArrayList <String> Domains = new ArrayList<String>(){{
    add("Game");
    add("General");
    add("Song");
  }};

   // example, [general, movie, programming]

  public String getInput()
  {
    Scanner Input = new Scanner(System.in);
    x=  Input.nextLine();
    return x;
  }

  public String Start()
  {
    return j.greet();
  }

  public String end()
  {
    return j.END();
  }


  Domain determineDomain()
  {

    if (x.contains("Game")||x.contains("game"))
    {
      Game a = new Game();
      j=a;
    }
    else if (x.contains("corona")||x.contains("Corona"))
    {
      General b=new General();
      j=b;
    }
    else if (x.contains("music")||x.contains("Music"))
    {
      Song c =new Song();
      j=c;
    }
    return j;
  }

  String answer()
  {
    String B=new String("");
    if (j.isQueryAmbiguous(x))
    {
      B=j.returnClearStatement();
    }
    else
    {
      B=j.REQ();
    }
    return B;
  }


  public String returnString(String q)
  {
    return j.topics(q);
  }

  private String x;
  private Domain j = new Domain();
}

class Domain
{

  private String [] array = new String []{"Hey There!","Hi!","Hello!"};
  private String []arr = new String []{"Bye!", "Goodbye.","See you later!"};



  public String greet()
  {
    Random rand = new Random();
    return array[rand.nextInt(3)];
  }
  public String END()
  {
    Random rand = new Random();
    return arr[rand.nextInt(3)];
  }

  public String REQ(){
    return "This Domain does not exist";
  }

  boolean isQueryAmbiguous(String topic)
  {
    boolean B;
    if (topic.contains("Corona")||topic.contains("corona"))
    {
      B=true;
    }
    else
    {
      B=false;
    }
    return B;
  }

  public String topics(String h)
  {
    return "error";
  }
  public String returnClearStatement()
  {
    return "error";
  }

}

class Game extends Domain
{

  private ArrayList <String> request = new ArrayList<String>(){{
    add("What type of game are you interested in ?");

  }};
  private ArrayList <String> arcade = new ArrayList<String>(){{
    add("Pac-Man");
    add("Blizzard");
  }};

  private ArrayList<String> war = new ArrayList<String>(){{
    add("Battlefield 1");
    add("COD MW3");
  }};

  private TreeMap<String,ArrayList<String>> map = new TreeMap<String,ArrayList<String>>()
  {{
    put("PS5Game_request",request);
    put("Arcade_request", arcade);
    put("War_request", war);
  }};

  public String REQ(){
    return (map.get("PS5Game_request")).get(0);
  }


  public String topics(String query)
  {
    Random r = new Random();
    //return array[rand.nextInt(3)];
    if (query.contains("arcade")||query.contains("Arcade"))
    {
      str=(map.get("Arcade_request")).get(r.nextInt(2));
    }
    else if (query.contains("War")||query.contains("war"))
    {
      str=(map.get("War_request")).get(r.nextInt(2));
    }
    return str;
  }
  private String str;

}







class General extends Domain
{


  private ArrayList <String> clarification = new ArrayList<String>(){{
    add("Are referring to the virus ?");
    add("Are you referring to the beer?");
  }};

  private ArrayList <String>Corona= new ArrayList<String>(){{
    add("Coronavirus disease (COVID-19) is an infectious disease caused by a newly discovered coronavirus.(WHO)");
    add("Corona Extra is a pale lager produced by Mexican brewery Cervecería Modelo and owned by Belgian company AB InBev.");
  }};

  private TreeMap<String,ArrayList<String>> map = new TreeMap<String,ArrayList<String>>()
  {{
    put("Corona_request",clarification);
    put("Cor_request", Corona);

  }};

  public String returnClearStatement(){
    Random c=new Random();
    return (map.get("Corona_request")).get(c.nextInt(2));
  }



  public String topics(String query)
  {
    if (query.contains("virus"))
    {
      str=(map.get("Cor_request")).get(0);
    }
    else if (query.contains("beer"))
    {
      str=(map.get("Cor_request")).get(1);
    }
    return str;
  }
  private String str;
}





class Song extends Domain{


  private ArrayList <String> request = new ArrayList<String>(){{
    add("What genre are you interested in ?");

  }};

  private ArrayList <String>Alt = new ArrayList<String>(){{
    add("Dominic Fike - 3 nights");
    add("AJR - Bang");
  }};

  private ArrayList <String>HipHop = new ArrayList<String>(){{
    add("Kanye west");
    add("NAS");
  }};

  private TreeMap<String,ArrayList<String>> map = new TreeMap<String,ArrayList<String>>()
  {{
    put("Song_request",request);
    put("Alternative_request", Alt);
    put("Hiphop_request", HipHop);
  }};

  public String REQ(){
    return (map.get("Song_request")).get(0);
  }
  public String topics(String query)
  {
    Random r = new Random();
    if (query.contains("Alternative")||query.contains("alternative"))
    {
      str=(map.get("Alternative_request")).get(r.nextInt(2));
    }
    else if (query.contains("Hiphop")||query.contains("hiphop"))
    {
      str=(map.get("Hiphop_request")).get(r.nextInt(2));
    }
    return str;
  }
  private String str;
}




public class ChatBot1
{
  public static void main (String[]args)
  {
    Chatbot x = new Chatbot();

    String c;
    Scanner myS = new Scanner(System.in);

    System.out.print("User: ");
    c = myS.nextLine();
    System.out.print("Chatbot: ");
    System.out.println(x.Start());
    System.out.print("User: ");
    c = x.getInput();

    while(!(c.contains("bye")||c.contains("Bye")))
    {
      System.out.print("Chatbot: ");
      x.determineDomain();
      System.out.println(x.answer());
      System.out.print("User: ");
      c = x.getInput();
      System.out.print("Chatbot: ");
      System.out.println(x.returnString(c));
      System.out.print("User: ");
      c = x.getInput();
     }
     System.out.print("Chatbot: ");
     System.out.println(x.end());

  }
}


/*
--------------------------------------------------
Chat sample
--------------------------------------------------

User: Hey
Chatbot: Hey there!
User: What movie should I watch?
Chatbot: What genre are you interested in?
User: I'm interested in Thriller
Chatbot: Limitless
User: What is Limitless about?
Chatbot: The movie is about gaining more intelligence using a mystery pill!
User: Who is Mike Myers?
Chatbot: Are you referring to the programming language?
User: Yes, I'm referring to the programming language
Chatbot: It's an object oriented programming language.

--------------------------------------------------
Program workflow
--------------------------------------------------
Loop
1. Get input from console
2. Determine the domain
3. find topic
4. Determine if query is ambiguous
5. Look up knowledge base and produce a response (randomize for multiple responses)
--------------------------------------------------

*/
