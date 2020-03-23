
# Solution 
User/System can provide the "Sentence" and solution need to find the longest or the shortest word & its length from input
## Requirements
1. Input can come from following sources
     Parameter in URL <br />
     Json, xml <br />
     Excel , csv, flatfiles <br />
     Some kind of Stream -Kafka, kinesis etc <br />
     Database <br />
     ... <br />   
2. User/System can send sentence in different language so solution need to compatible with char encoding 

3. Case-sestivity will be define by user/system as parameter. BY default, It will be case-senstive.
# Steps performed
Initialize the project via spring starter https://start.spring.io/ <br />
dryrun get api endpoint`/longword?sentence=x` without logic e.g.<br />
`http://localhost:2303/longword?sentence=This is SpringBoot application`
## TDD Approach - Junit-5, Springboottest , Mockito 
    Test-1: getLongestWordWithLength() , A positive test to get longest word in sentence
    { The cow jumped over the moon. : jumped }
    
    Test-2 getShortestWordWithLength() , A positive test to get shortest word in sentence
     { The cow jumped over moon. : The }

