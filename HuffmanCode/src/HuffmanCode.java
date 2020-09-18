import java.io.*;
import java.util.*;

public class HuffmanCode {

    private static Map<Byte, String> huffmanCode = new HashMap<>();

    private static StringBuilder stringBuilder = new StringBuilder();

    public static void main(String[] args) {
        String str = "i like like like java do you like a java";
        byte[] bytes = str.getBytes();
        /**
         ArrayList<Node> nodes = getNodes(bytes);
        //System.out.println(nodes);
        Node huffmanTree = createHuffmanTree(nodes);
        preOrder(huffmanTree);
        Map<Byte, String> code = getCode(huffmanTree);
        System.out.println(code);
        byte[] zip = zip(bytes, huffmanCode);
        System.out.println(Arrays.toString(zip));
         */
//        byte[] bytes1 = huffmanZip(bytes);
//        System.out.println(Arrays.toString(bytes1));
//        byte[] decode = decode(huffmanCode, bytes1);
//        System.out.println(new String(decode));
        String path = "E:\\preview1.jpg";
        String dstFile = "E:\\1.zip";
        //zipFile(path,dstFile);
        unZipFile(dstFile,path);
    }

    //解压文件
    public static void unZipFile(String filePath, String desFile){
        ObjectInputStream inputStream = null;
        InputStream is = null;
        OutputStream outputStream = null;
        try {
            is = new FileInputStream(filePath);
            inputStream = new ObjectInputStream(is);
            byte[] bytes = (byte[]) inputStream.readObject();
            Map<Byte,String> map = (Map<Byte, String>) inputStream.readObject();
            byte[] bytes1 = decode(map,bytes);
            outputStream = new FileOutputStream(desFile);
            outputStream.write(bytes1);
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
                inputStream.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //压缩文件
    public static void zipFile(String filePath, String desFile){
        FileInputStream fileInputStream = null;
        OutputStream outputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
             fileInputStream = new FileInputStream(filePath);
            byte[] bytes = new byte[fileInputStream.available()];
            fileInputStream.read(bytes);
            byte[] huffmanByte = huffmanZip(bytes);
            outputStream = new FileOutputStream(desFile);
            objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(huffmanByte);
            objectOutputStream.writeObject(huffmanCode);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fileInputStream.close();
                outputStream.close();
                objectOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static byte[] decode(Map<Byte,String> huffmanCode,byte[] huffmanBytes){
        StringBuilder stringBuilder1 = new StringBuilder();
        for (int i = 0; i < huffmanBytes.length; i++) {
            byte b = huffmanBytes[i];
            boolean flag = (i == huffmanBytes.length - 1);
            stringBuilder1.append(byteToBitString(!flag,b));
        }
        Map<String, Byte> map = new HashMap<>();
        for (Map.Entry<Byte,String> entry:huffmanCode.entrySet()){
            map.put(entry.getValue(),entry.getKey());
        }
        ArrayList<Byte> list = new ArrayList<>();
        for (int i = 0; i < stringBuilder1.length();) {
            int count = 1;
            boolean flag = true;
            Byte b = null;
            while (flag){
                String key = stringBuilder1.substring(i, i + count);
                if (map.get(key) != null) {
                    b = map.get(key);
                    flag = false;
                } else {
                    count ++;
                }
            }
            list.add(b);
            i += count;
        }
        byte[] bytes = new byte[list.size()];
        for (int i = 0; i < list.size(); i++) {
            bytes[i] = list.get(i);
        }
        return bytes;
    }

    //返回的是补码
    public static String byteToBitString(boolean flag, byte b){
        int temp = b;
        if (flag) {
            temp |= 256;
        }
        String s = Integer.toBinaryString(temp);//返回的是temp二进制对应的补码
        if (flag) {
            return s.substring(s.length() - 8);
        } else {
            return s;
        }
    }

    public static byte[] huffmanZip(byte[] bytes){
        ArrayList<Node> nodes = getNodes(bytes);
        Node huffmanTree = createHuffmanTree(nodes);
        Map<Byte, String> code = getCode(huffmanTree);
        byte[] zip = zip(bytes, code);
        return zip;
    }

    //将字符串对应的byte数组，通过生成的哈夫曼编码表，返回一个压缩后的哈夫曼byte数组
    public static byte[] zip(byte[] bytes,Map<Byte,String> map){
        StringBuilder stringBuilder1 = new StringBuilder();
        for (byte b:bytes) {
            stringBuilder1.append(map.get(b));
        }
        int length = 0;
        if (stringBuilder1.length() % 8 == 0) {
            length = stringBuilder1.length() / 8;
        } else {
            length = stringBuilder1.length() / 8 + 1;
        }
        byte[] bytes1 = new byte[length];
        int count = 0;
        for (int i = 0; i < stringBuilder1.length(); i+=8) {
            String str;
            if (i + 8 > stringBuilder1.length()) {
                str = stringBuilder1.substring(i);
            } else {
                str = stringBuilder1.substring(i,i+8);
            }
            bytes1[count] = (byte) Integer.parseInt(str,2);
            count ++;
        }
        return bytes1;
    }

    public static ArrayList<Node> getNodes(byte[] bytes){
        ArrayList<Node> nodes = new ArrayList<>();
        HashMap<Byte,Integer> hashMap = new HashMap<>();
        for (byte b:bytes) {
            Integer count = hashMap.get(b);
            if (count == null) {
                hashMap.put(b,1);
            } else {
                hashMap.put(b,count + 1);
            }
        }
        for (Map.Entry<Byte,Integer> entry:hashMap.entrySet()) {
            nodes.add(new Node(entry.getKey(),entry.getValue()));
        }
        return nodes;
    }

    //生成哈夫曼树
    public static Node createHuffmanTree(ArrayList<Node> list){
        while (list.size() > 1) {
            Collections.sort(list);
            Node leftNode = list.get(0);
            Node rightNode = list.get(1);
            Node parent = new Node(null,leftNode.getWeight() + rightNode.getWeight());
            parent.setLeft(leftNode);
            parent.setRight(rightNode);
            list.remove(leftNode);
            list.remove(rightNode);
            list.add(parent);
        }
        return list.get(0);
    }

    public static Map<Byte,String> getCode(Node root){
        if (root == null) {
            return null;
        }
        getCode(root.getLeft(),"0",stringBuilder);
        getCode(root.getRight(),"1",stringBuilder);
        return huffmanCode;
    }

    //生成哈夫曼树对应的哈夫曼编码，将生成的哈夫曼编码放在map<Byte, String>中
    public static  void getCode(Node node, String code, StringBuilder stringBuilder){
        StringBuilder stringBuilder1 = new StringBuilder(stringBuilder);
        stringBuilder1.append(code);
        if (node != null) {
            if (node.getData() == null) {
                getCode(node.getLeft(),"0",stringBuilder1);
                getCode(node.getRight(),"1",stringBuilder1);
            } else {
                huffmanCode.put(node.getData(),stringBuilder1.toString());
            }
        }
    }

    public static void preOrder(Node root){
        if (root != null) {
            root.preOrder();
        }else {
            System.out.println("空");
        }
    }
}
class Node implements Comparable<Node>{
    private Byte data;//字符本身
    private int weight;//出现次数
    private Node left;
    private Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    public void preOrder(){
        System.out.println(this);
        if (this.left != null){
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    public Byte getData() {
        return data;
    }

    public void setData(Byte data) {
        this.data = data;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}
