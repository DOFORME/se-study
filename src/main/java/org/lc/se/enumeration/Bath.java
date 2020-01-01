package org.lc.se.enumeration;

/**
 * @author lc
 */
public class Bath {

    enum Step {
        /**
         * 第一步
         */
        STEP1 {
            @Override
            boolean handle(Person person) {
                switch (person.getReady()) {
                    case YES:
                        System.out.println("我已经准备好了");
                        return true;
                    case NO:
                        System.out.println("我还没准备好怎么洗澡");
                        return false;
                    default:
                        System.out.println("我没说呢，你咋知道我要洗澡");
                        return false;
                }
            }
        },
        /**
         * 第二步
         */
        STEP2 {
            @Override
            boolean handle(Person person) {
                switch (person.getClothes()) {
                    case NOTHING:
                        System.out.println("衣服脱光了，可以洗了");
                        return true;
                    case SOME:
                        System.out.println("我衣服还没脱呢，咋洗？");
                        return false;
                    default:
                        System.out.println("我不知到有没有穿？？？！！！");
                        return false;
                }
            }
        },
        /**
         * 第三步
         */
        STEP3 {
            @Override
            boolean handle(Person person) {
                switch (person.getWater()) {
                    case HOT:
                        System.out.println("烫猪呢？");
                        return false;
                    case COLD:
                        System.out.println("冻饺子？");
                        return false;
                    case SUITABLE:
                        System.out.println("妙哉");
                        return true;
                    default:
                        System.out.println("不知道多少度，你去洗？");
                        return false;
                }
            }
        };

        abstract boolean handle(Person person);
    }

    public void takeBath(Person person) {
        boolean result = true;
        for (Step s : Step.values()) {
            boolean tmp = s.handle(person);
            if (!tmp) {
                System.out.println("不洗了");
                result = false;
                break;
            }
        }
        if (result) {
            System.out.println("洗完了，真舒服！！！");
        }
    }
}
