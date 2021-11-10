enum Signal {
  WINK, DOUBLE_BLINK, CLOSE_YOUR_EYES, JUMP;

  static Signal getValue(int i) {
    switch (i) {
      case 0:
        return WINK;
      case 1:
        return DOUBLE_BLINK;
      case 2:
        return CLOSE_YOUR_EYES;
      case 3:
        return JUMP;
    }
    return null;
  }
}
