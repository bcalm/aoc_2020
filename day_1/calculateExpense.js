const fs = require('fs');

const areCorrectEntries = (entry1, entry2) => {
  return Number.parseInt(entry1) + Number.parseInt(entry2) === 2020;
};

const getResult = (expensesList) => {
  for (let i = 0; i < expensesList.length; i++) {
    const entry1 = expensesList[i];
    for (let j = 0; j < expensesList.length; j++) {
      const entry2 = expensesList[j];
      if (areCorrectEntries(entry1, entry2)) {
        return entry1 * entry2;
      }
    }
  }
};

const main = function () {
  const expensesList = fs.readFileSync('./expenses.txt', 'utf8').split('\n');
  const answer = getResult(expensesList);
  console.log(answer);
};

main();
