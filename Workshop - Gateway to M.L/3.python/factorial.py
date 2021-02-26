#Factorial input number n using recurssion
def Factorial(n):
    if (n ==1 ):
        return 1
    else:
        return n*Factorial(n-1)
print('Input a Number')
number = int(input())
if (number==0):
    print('Factorial of 0 is 1')
elif(number<0):
    print('sorry factorial doesnot exist !! ')
else:
    print('Factorial of '+str(number)+ ' is '+str(Factorial(number)))
    
