DotLoop Pepsi Challenge One - 1.0
---------------------------------

-- What is the Challenge?

The DotLoop Pepsi Challenge was conceived of when Moe asked the interview question:

    If you were given a one gig text file with random lines of data and were tasked with identifying duplicate lines, how would you approach the problem?
    
This led to vigorous debate on the best way to solve this problem. There are different definitions of "best" way, but for the sake of this initial challenge the "best" way will be the approach that runs in the least amount of time. Memory usage is not considered.


-- How do I participate?

To participate in the challenge you must write a class that implements the com.dotloop.pepsi.Contender interface. This interface is very simple:

    package com.dotloop.pepsi;
    
    import java.util.Set;
    
    import com.dotloop.pepsi.support.EpicFail;
    
    public interface Contender {
        public Set<String> identifyDuplicates(String fileName) throws EpicFail;
    }

Create a subpackage of com.dotloop.pepsi.contender to hold your Contender and any of it's dependencies. 

Once you have a contender, add it to the com.dotloop.pepsi.PepsiChallenge class in the loadContenders() method:

	private static void loadContenders() {
	    // Contenders go here
		contenders.add(new MoeContenderTwo());
		contenders.add(new MoeContenderOne());
	}
	
While doing development and debugging it might be useful to comment out Contenders you don't care about.

It's probably smart to start with a smaller sized data file during initial development and testing. To reduce the size of the data file change the MAXIMUM_FILE_SIZE constant in DataGenerator.java, delete the existing data file, and rerun the challenge.


-- Where do I get the data file?

A random data file will be generated when the Pepsi Challenge is run for the first time. To get a new data file just delete the old one and rerun the challenge. See DataGenerator.java for details on how the data file is created.


-- How do I run the challenge?

You can run the PepsiChallenge class in your IDE or on the command line. Either way you'll want to specify and minimum and maxmimum heap size to eliminate memory as a limiting factor. For example, in my environment I'm running the challenge at the command line using the Eclipse compiled class files:

    java -Xmx4G -Xms4G -classpath ./bin com.dotloop.pepsi.PepsiChallenge
    
The inital two implementations need > 3G of heap to run. I'm working on reducing that, it's all part of the challenge.





-- WHAT ARE THE RULES?

It's impossible to anticipate all of the ways that someone could cheat, so in general we'll start with:

* Don't cheat

But to try to formalize that:

* No modifying the file
* Threading is OK to use, but all threads must complete before returnng from the indentifyDuplicates() call. No leaving a heavy CPU thread running to screw the competitors.
* No using memory in a way such that a normal garbage collection run won't clean up whatever your Contender did.
