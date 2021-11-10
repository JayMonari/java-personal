import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class DiffieHellman {

  public BigInteger privateKey(BigInteger prime) {
    int randomPrivateKey;
    try {
      randomPrivateKey = SecureRandom.getInstanceStrong()
        .nextInt(prime.intValue() - 1) + 1;
    } catch (NoSuchAlgorithmException e) {
      randomPrivateKey = 0;
    }
    return BigInteger.valueOf(randomPrivateKey);
  }

  public BigInteger publicKey(BigInteger primeA, BigInteger primeB, BigInteger privateKey) {
    return generateKey(privateKey, primeB, primeA);
  }

  public BigInteger secret(BigInteger prime, BigInteger publicKey, BigInteger privateKey) {
    return generateKey(privateKey, publicKey, prime);
  }

  private BigInteger generateKey(BigInteger primeA, BigInteger primeB, BigInteger modPrime) {
    if (primeB.compareTo(BigInteger.ONE) == 0) {
      return primeA;
    }
    return BigInteger.valueOf((long) Math.pow(primeB.longValue(), primeA.longValue()) % modPrime.longValue());
  }
}
