
# Runing instruction 
Test using springboottest using randomport `clean test`
Run application on port:2303 `clean spring-boot:run` and hit following end point
`http://localhost:2303/longword?sentence=The cow jumped over the moon.`

# Solution 
User/System can provide the "Sentence" and solution need to find the longest or the shortest word & its length from input
## Requirements
1. Input can come from following sources<br />
     Parameter in URL <br />
     Command line argument <br />
     Json, xml <br />
     Excel , csv, flatfiles <br />
     Some kind of Stream -Kafka, kinesis etc <br />
     Database <br />
     ... <br />   
2. User/System can send sentence in different language so solution need to compatible with char encoding <br /> 
3. Case-sestivity will be define by user/system as parameter. By default, It will be case-senstive.<br /> 
4. All Same length longest word will return by solution <br /> 
5. Apart from " " , other charachter will be part of word. Exception is "." period chars.<br /> 
N.B. The above point are assumption. Due to time constraint, only tests in next section has been implemented <br /> 
# Steps performed
Initialize the project via spring starter https://start.spring.io/ <br />
dryrun get api endpoint`/longword?sentence=x` without logic e.g.<br />
`http://localhost:2303/longword?sentence=This is SpringBoot application`
## TDD Approach - Junit-5, Springboottest , Mockito 
    Test-1: getLongestWordWithLength() , A positive test to get longest word in sentence
    { The cow jumped over the moon. : jumped }
    
    Test-2 getShortestWordWithLength() , A positive test to get shortest word in sentence
     { The cow jumped over moon. : The }
     
    Test-3: manageNullSentence() , Verify null input for getLongestWord method
    { Please provide the non-null Input : 0 }  
    
    Test-4: manageEmptySentence() , Verify Empty input for getLongestWord method
    { Please provide the non-empty Input : 0 }
    
    Test-5: multipleLargestWordsInSentence() , Two same length longest word should return both words
    { The monkey and cow and jumped over the moon  : monkey, 6 ; jumped , 6}
    
    Test-6: sendOnlySpaceInSentence() , Verify space only string ("    ")
    { You have wrongly provided space-only input: 0}
            
    Test-6: hypenatedLongedWordInSentence() , Verify hypenated longest string
    { the worst in-a-recession year. : 14}
    
    Test-7: onlyhypenatedLongedWordInSentence() , Verify hypenated longest string
    { the worst in-a-recession year. : 29}
    
    Test-8: periodCharNotCountedInWord , period is not counted in the word jupiter.
    { The cow jumped over the jupiter. : 7}
    
    

