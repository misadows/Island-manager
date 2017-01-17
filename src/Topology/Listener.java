package Topology;

import Model.Result;
import akka.actor.UntypedActor;

public class Listener extends UntypedActor
{
	  public void onReceive(Object message)
	  {
	    if (message instanceof Result) 
	    {
	      Result result = (Result) message;
	      System.out.println("result: ");
	          
	      getContext().system().shutdown();
	    } 
	    else 
	    {
	      unhandled(message);
	    }
	  }
}