import time
import random

# Generate a long random string and pattern
def generate_string(length):
    return ''.join(random.choices('ABCD', k=length))

text_50k = generate_string(50000)
pattern_1k = generate_string(1000)

# Naive String Matching Algorithm
def naive_string_matching(text, pattern):
    n = len(text)
    m = len(pattern)
    
    start_time = time.time()
    for i in range(n - m + 1):
        match = True
        for j in range(m):
            if text[i + j] != pattern[j]:
                match = False
                break
        if match:
            print(f"Pattern found at index {i}")
    end_time = time.time()
    print(f"Naive String Matching took {end_time - start_time:.5f} seconds")

# Rabin-Karp Algorithm
def rabin_karp(text, pattern, d=256, q=101):
    n = len(text)
    m = len(pattern)
    h = pow(d, m-1) % q
    p = 0
    t = 0
    
    start_time = time.time()
    for i in range(m):
        p = (d * p + ord(pattern[i])) % q
        t = (d * t + ord(text[i])) % q
    
    for i in range(n - m + 1):
        if p == t:
            if text[i:i+m] == pattern:
                print(f"Pattern found at index {i}")
        if i < n - m:
            t = (d * (t - ord(text[i]) * h) + ord(text[i + m])) % q
            if t < 0:
                t += q
    end_time = time.time()
    print(f"Rabin-Karp Algorithm took {end_time - start_time:.5f} seconds")

# Knuth-Morris-Pratt (KMP) Algorithm
def compute_lps(pattern):
    m = len(pattern)
    lps = [0] * m
    j = 0
    i = 1
    while i < m:
        if pattern[i] == pattern[j]:
            j += 1
            lps[i] = j
            i += 1
        else:
            if j != 0:
                j = lps[j - 1]
            else:
                lps[i] = 0
                i += 1
    return lps

def kmp_string_matching(text, pattern):
    n = len(text)
    m = len(pattern)
    lps = compute_lps(pattern)
    
    start_time = time.time()
    i = j = 0
    while i < n:
        if pattern[j] == text[i]:
            i += 1
            j += 1
        if j == m:
            print(f"Pattern found at index {i - j}")
            j = lps[j - 1]
        elif i < n and pattern[j] != text[i]:
            if j != 0:
                j = lps[j - 1]
            else:
                i += 1
    end_time = time.time()
    print(f"KMP Algorithm took {end_time - start_time:.5f} seconds")

# Finite Automata String Matching Algorithm
def build_transition_function(pattern, alphabet):
    m = len(pattern)
    transition = [{} for _ in range(m + 1)]
    
    for state in range(m + 1):
        for char in alphabet:
            prefix = pattern[:state] + char
            for k in range(state + 1, -1, -1):
                if prefix.endswith(pattern[:k]):
                    transition[state][char] = k
                    break
    return transition

def finite_automata_string_matching(text, pattern):
    n = len(text)
    m = len(pattern)
    alphabet = set(text)
    transition = build_transition_function(pattern, alphabet)
    state = 0
    
    start_time = time.time()
    for i in range(n):
        state = transition[state].get(text[i], 0)
        if state == m:
            print(f"Pattern found at index {i - m + 1}")
    end_time = time.time()
    print(f"Finite Automata Algorithm took {end_time - start_time:.5f} seconds")

# Example usage
if __name__ == "__main__":
    naive_string_matching(text_50k, pattern_1k)
    rabin_karp(text_50k, pattern_1k)
    kmp_string_matching(text_50k, pattern_1k)
    finite_automata_string_matching(text_50k, pattern_1k)