clear all;
close all;

[a1, a2, a3, a4, tr] = textread("Iris_data_norm_train.txt", "%f, %f, %f, %f, %f");
Y = [tr];

[b1, b2, b3, b4, te] = textread("iris_data_norm_test.txt", "%f, %f, %f, %f, %f");
N = length(tr);

for k=1:1:110
j(k) = 1;
endfor

X = [j', a1, a2, a3, a4, a1.*a2, a2.*a3, a3.*a4, a4.*a1, a4.*a2, a3.*a1, a1.^2, a2.^2, a3.^2, a4.^2, a1.*a2.*a3.*a4];
Xp = (inv(X'*X))*X';
w = Xp*Y;		
		

printf("New Weights are :");
w

errordata = 0;

for i = 1:length(te)
	data = [j(i), a1(i), a2(i), a3(i), a4(i), a1(i).*a2(i), a2(i).*a3(i), a3(i).*a4(i), a4(i).*a1(i), a4(i).*a2(i), a3(i).*a1(i), a1(i).^2, a2(i).^2, a3(i).^2, a4(i).^2, a1(i).*a2(i).*a3(i).*a4(i)];
	
	hypothesis = data * w;

	if((hypothesis > 0 && te(i) == -1) || (hypothesis < 0 && te(i) == 1))
		errordata++;
	end
end

printf("Error Data = %d \n", errordata);

