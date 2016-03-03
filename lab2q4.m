
clear all;
close all;

% reading train dataset
[a1, a2, a3, a4, tr] = textread("iris_data_norm_both.txt", "%f, %f, %f, %f, %f");

% length of train dataset

m=load("iris_data_norm_both.txt");
s = size(m);
% length of train dataset


for k= 1:1:100
	error_inside_sample(k)=0;
	error_outside_sample(k)=0;
endfor

%to take the input for percent
x = input("enter the percentage for training data")

N = (s(1)*x)/100;

% initial weights are a random number
w(1:(s(2)-1)) = rand()*1000;



errors = 0;
iterations = 0;

k = 1;
flag = false;

while(k<=100)
i=1;
while(i <= N)

	
	data = m(i, 1:s(2)-1);
	hypothesis = w * data';

	if(hypothesis < 0 && m(i,s(2)) == 1 && flag != true)
		w = w + data;	
	endif	
	if(hypothesis > 0 && m(i,s(2)) == -1 && flag != true)
		w = w - data;	
	endif
	if((m(i,s(2))==-1 && hypothesis>0)||(m(i,s(2))==1 && hypothesis<0))
		error_inside_sample(k)++;
	endif
 	
	if (k>=2)
	
	if(error_inside_sample(k) >= error_inside_sample(k-1))
	 	flag = true;
	else
		flag = false;
	endif
	
	endif
	
 
       	i++;
	
endwhile
i=N;
while(i<150)
	i=i+1;
	data = m(i, 1:s(2)-1);
	hypothesis = w * data';
	if((m(i,s(2))==1 && hypothesis<0)|| (m(i,s(2))==-1 && hypothesis>0))
		error_outside_sample(k)++;
	endif
endwhile
k++;
endwhile
plot(error_inside_sample, 'r')
hold on
plot(error_outside_sample,'g')
hold off
legend('error_in_sample', 'error_out_sample');
save -append -text einfile error_inside_sample
save -append -text eoutfile error_outside_sample
