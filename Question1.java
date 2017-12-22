package test.moneyview.com;

public class Question1 {

	
	bool modularSum(int arr[], int n, int m)
	{
		if (n > m)
			return true;

		bool DP[m];
		memset(DP, false, m);

		// we'll loop through all the elements of arr[]
		for (int i=0; i<n; i++)
		{
			// anytime we encounter a sum divisible
			// by m, we are done
			if (DP[0])
				return true;

			bool temp[m];
			memset(temp,false,m);


			for (int j=0; j<m; j++)
			{
				// if an element is true in DP table
				if (DP[j] == true)
				{
					if (DP[(j+arr[i]) % m] == false)

						// We update it in temp and update
						// to DP once loop of j is over
						temp[(j+arr[i]) % m] = true;
				}
			}

			// Updating all the elements of temp
			// to DP table since iteration over
			// j is over
			for (int j=0; j<m; j++)
				if (temp[j])
					DP[j] = true;


			// Also since arr[i] is a single element
			// subset, arr[i]%m is one of the possible
			// sum
			DP[arr[i]%m] = true;
		}

		return DP[0];
	}

	public static void main(String[] args) {
	
		int arr[] = {1, 7};
		int n = sizeof(arr)/sizeof(arr[0]);
		int m = 5;

		modularSum(arr, n, m) ? cout << "YESn" :
								cout << "NOn";

		return 0;
	}
}
