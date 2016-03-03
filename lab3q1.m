
clear all;
close all;

m=load("ex2data2.txt");
s = size(m);
[row col]=size(m); 


%to take the input for percent

N = 118;

% initial weights are a random number
w(1:(s(2)-1)) = 0;

learning_rate = 0.1;
k = 1;
sum1 = 0;
grad = 0;
grad1= 0;

i=1;
while(i <= N)
	
	data = m(i, 1:s(2)-1);
	hypothesis = m(i,s(2)) * data';
	grad1 = 1 + exp(norm(m(i,s(2))*w'*data));
	
	for t=0:1:N
	 	sum1 = sum1 + (hypothesis'/grad1);		
	endfor
	
	grad = (-1/N)*sum1;

	w = w - learning_rate*grad;
       	i++;

endwhile
hold on
plot(grad)
