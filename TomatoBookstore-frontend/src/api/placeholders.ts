export const loadPlaceholderUsers = () => {
  return [
    {
      username: "SanZhang",
      name: "张三",
      role: "admin",
    },
    {
      username: "SiZhang",
      name: "张四",
      role: "user",
    },
    {
      username: "SiLi",
      name: "李氏",
      role: "admin",
    },
  ];
};

export const loadPlaceholderProducts = () => {
  return [
    {
      id: "101",
      title: "深入理解Java虚拟机",
      price: 99.5,
      rate: 9.5,
      description: "Java开发者必读经典，全面讲解JVM工作原理",
      cover: "https://example.com/covers/jvm.jpg",
      detail: "本书详细讲解了Java虚拟机的体系结构、内存管理、字节码执行等核心内容",
      specifications: [
        {
          id: "1001",
          item: "作者",
          value: "周志明",
          productId: "101",
        },
        {
          id: "1002",
          item: "副标题",
          value: "JVM高级特性与最佳实践",
          productId: "101",
        },
        {
          id: "1003",
          item: "ISBN",
          value: "9787111421900",
          productId: "101",
        },
        {
          id: "1004",
          item: "装帧",
          value: "平装",
          productId: "101",
        },
        {
          id: "1005",
          item: "页数",
          value: "540",
          productId: "101",
        },
        {
          id: "1006",
          item: "出版社",
          value: "机械工业出版社",
          productId: "101",
        },
        {
          id: "1007",
          item: "出版日期",
          value: "2013-09-01",
          productId: "101",
        },
      ],
    },
    {
      id: "102",
      title: "代码整洁之道",
      price: 59.0,
      rate: 9.2,
      description: "软件工程领域的经典著作",
      cover: "https://example.com/covers/clean-code.jpg",
      detail: "本书提出一种观念：代码质量与其整洁度成正比",
      specifications: [
        {
          id: "1008",
          item: "作者",
          value: "Robert C. Martin",
          productId: "102",
        },
        {
          id: "1009",
          item: "副标题",
          value: "程序员的职业素养",
          productId: "102",
        },
        {
          id: "1010",
          item: "ISBN",
          value: "9787121316633",
          productId: "102",
        },
        {
          id: "1011",
          item: "装帧",
          value: "精装",
          productId: "102",
        },
        {
          id: "1012",
          item: "页数",
          value: "388",
          productId: "102",
        },
        {
          id: "1013",
          item: "出版社",
          value: "人民邮电出版社",
          productId: "102",
        },
        {
          id: "1014",
          item: "出版日期",
          value: "2018-01-01",
          productId: "102",
        },
      ],
    },
  ];
};
