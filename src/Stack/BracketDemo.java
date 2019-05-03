package Stack;


public class BracketDemo {
    public static void main(String[] args){

        String str1 = "<<(()))>>";
        String str2 = "<<(())>>";

        System.out.println(checkBracket(str1));
        System.out.println(checkBracket(str2));

    }

    public static boolean checkBracket(String str){
        ArrayStack<Character> stack = new ArrayStack<Character>();
        if (str == null) {
            return false;
        }
        char[] c = str.toCharArray();
        for(int i = 0;i < c.length; i++){
            if((c[i]=='{')||(c[i]=='[')||(c[i]=='(')||(c[i]=='<')) {
                stack.push(c[i]);
                continue;
            }
            if(c[i]=='}'){
                if(!stack.isEmpty()&&stack.getPeek().equals('{')) {
                    stack.pop();
                    continue;
                }
                else
                    return false;
            }

            if(c[i]==']'){
                if(!stack.isEmpty()&&stack.getPeek().equals('[')) {
                    stack.pop();
                    continue;
                }
                else
                    return false;
            }

            if(c[i]==')'){
                if(!stack.isEmpty()&&stack.getPeek().equals('(')) {
                    stack.pop();
                    continue;
                } else
                    return false;
            }

            if(c[i]=='>'){
                if(!stack.isEmpty()&&stack.getPeek().equals('<')) {
                    stack.pop();
                    continue;
                }
                else
                    return false;
            }
        }
        if(!stack.isEmpty())
            return false;
        return true;
    }
}
