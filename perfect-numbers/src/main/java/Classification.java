enum Classification {
  DEFICIENT, PERFECT, ABUNDANT;

  static Classification getClassification(int n) {
    switch (n) {
      case -1:
        return DEFICIENT;
      case 0:
        return PERFECT;
      case 1:
        return ABUNDANT;
      default:
        throw new UnknownError("compareTo() will make sure to never get here");
    }
  }
}
