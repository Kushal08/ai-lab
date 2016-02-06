
clear all;
close all;

% reading train dataset
[a1, a2, a3, a4, tr] = textread("iris_data_norm_both.txt", "%f, %f, %f, %f, %f");

% length of train dataset

for k= 1:1:100
	error_inside_sample(k)=0;
	error_outside_sample(k)=0;
endfor

N = 105;

% initial weights are a random number
w(1:4) = rand()*1000;

errors = 0;
iterations = 0;

k = 1;

while(k<=100)
i=1;
while(i <= N)

	
	data = [a1(i), a2(i), a3(i), a4(i)];
	
	hypothesis = w * data';
	if(hypothesis < 0 && tr(i) == 1)
		w = w + data;	
	endif	
	if(hypothesis > 0 && tr(i) == -1)
		w = w - data;	
	endif

	if((tr(i)==-1 && hypothesis>0)||(tr(i)==1 && hypothesis<0))
		error_inside_sample(k)++;
	endif
 
       	i++;
	
endwhile
i=N;
while(i<150)
	i=i+1;
	data = [a1(i), a2(i), a3(i), a4(i)];
	hypothesis = w * data';
	if((tr(i)==1 && hypothesis<0)||(tr(i)==-1 && hypothesis>0))
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
