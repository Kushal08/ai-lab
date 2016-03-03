clear all
close all
data = load("ex2data2.txt"); 
[row col]=size(data); 

z = data(:,1:col-1); 
y = data(:,col);
err = 0;
acc =0;
acc = 0;
miss = 0;
row1 = 0;
w = zeros(1,col-1);
learning_rate = 1;
iteration=row;

error=zeros(row,1);

for i=1:200
	grad1 = zeros(1,col-1);
	kkk = size(grad1);
	error_sum=0;
	miss = 0;
	for j=1:118
		x =[z(j,1) z(j,2)];	
		h = 1./(1+exp(-x*w'));
		grad1 = grad1 + ((y(j)*x)/(1+exp(y(j)*w*x')));

		h = 1/(1+exp(-x*w'));
		if(h>0.5 && y(j) == 0)
		     err = 1;
		else if(h<0.5 && y(j) == 1)
		     err= 1;
		else 
		     err = 0;
		end
		end
		
		miss = miss+ err;
	endfor
	w = w + (learning_rate/row) * grad1;
	for j=1:118
		x =[z(j,1) z(j,2)];	
		error_sum = error_sum + log(1+exp(-y(j)*w*x'));
	
	endfor
	
	error(i)= error_sum /row;
		
endfor

plot(error);

xlabel("Iterations");
ylabel("Ein Error");
title("plot of Ein Error");
printf("Accurancy:\n");

