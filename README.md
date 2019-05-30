# Reduce Dimensionality of High Dimensional Vectors
To Run:
```
rd.bat
```
Result:
300-D word vectors are reduced to graphically representable 2-D vectors.
<img src="words.gif" width="350px"/>


# Entropy
Experimenting with entropy and information theory.  
Reference: https://en.wikipedia.org/wiki/Entropy

## Shannon Entropy (H):
- Shows how "random" or "compressed" the data is  
- Calculation:  
![shannon entropy equation](https://wikimedia.org/api/rest_v1/media/math/render/svg/7de5d59a442f5305853d4392826b1f51dc43f6d0)  
- Example Run:
```
$> compare_entropy.py

Compare Shannon Entropy (H) of .bmp, .jpg, and random files
Expected Result: H_bmp < H_jpg < H_random

Entropy for .bmp file: 0.40725679430490014
Entropy for .jpg file: 0.9558639704451859
Entropy for random text: 0.9984971607415577

```
