clear all;
close all;

[a1, a2, a3, a4, tr] = textread("Iris_data_norm_train.txt", "%f, %f, %f, %f, %f");
X = [a1 a2 a3 a4];
Y = [tr];

[b1, b2, b3, b4, te] = textread("iris_data_norm_test.txt", "%f, %f, %f, %f, %f");
N = length(tr);

Xp = (inv(X'*X))*X';
w = Xp*Y;		

printf("New Weights are = (%f, %f, %f, %f)\n", w(1), w(2), w(3), w(4));

errordata = 0;

for i = 1:length(te)
	data = [b1(i), b2(i), b3(i), b4(i)];
	
	hypothesis = data * w;

	if((hypothesis > 0 && te(i) == -1) || (hypothesis < 0 && te(i) == 1))
		errordata++;
	end
end

printf("Error Data = %d \n", errordata);

