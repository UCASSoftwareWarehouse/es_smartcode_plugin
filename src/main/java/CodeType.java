public enum CodeType {
    Python("python"), Golang("golang"), Java("java");


    private final String codeTypeStr;
    CodeType(String codeTypeStr) {
        this.codeTypeStr = codeTypeStr;
    }

    public String getCodeTypeStr() {
        return this.codeTypeStr;
    }

    @Override
    public String toString() {
        return getCodeTypeStr();
    }
}
