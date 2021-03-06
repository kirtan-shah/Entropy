import math
import random

bmp = open("low.bmp", "rb")
jpg = open("high.jpg", "rb")

def rand_str(size):
    string = ""
    for i in range(0, size):
        string += chr(random.randint(0, 256))
    return string

# Shannon Entropy (H):
# H = - sum_i(p_i * log_b(p_i))
# p_i is probability of value appearing in message
# b is number of possible values

def log_b(b, x):
    return math.log(x) / math.log(b)

def entropy(text):
    H = 0
    plist = {}
    numc = 0
    for ch in text:
        if not ch in plist:
            plist[ch] = 1
        else:
            plist[ch] += 1
        numc += 1
    for ch in plist:
        p_i = plist[ch] / numc
        H += p_i * log_b(256, p_i)
    return -H

print("\nCompare Shannon Entropy (H) of .bmp, .jpg, and random files")
print("Expected Result: H_bmp < H_jpg < H_random\n")
print("Entropy for .bmp file: " + str(entropy(bmp.read())))
print("Entropy for .jpg file: " + str(entropy(jpg.read())))
print("Entropy for random text: " + str(entropy(rand_str(10000))))
print()
