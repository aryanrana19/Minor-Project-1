// Function to Fetch Data
async function fetchData(endpoint) {
  try {
    const response = await fetch(endpoint);
    return await response.json();
  } catch (error) {
    console.error(`Error fetching data from ${endpoint}:`, error);
  }
}


async function renderPieChart() {
  const data = await fetchData("/api/data/column_a");

  const categorizedData = { Positive: 0, Negative: 0, Natural: 0 };
  data.forEach((item) => {
    if (item.score === 1) categorizedData.Positive += 1;
    else if (item.score === 2) categorizedData.Negative += 1;
    else if (item.score === 0) categorizedData.Natural += 1;
  });

  const ctx = document.getElementById("pieChart").getContext("2d");
  new Chart(ctx, {
    type: "pie",
    data: {
      labels: ["Positive", "Negative", "Natural"],
      datasets: [
        {
          data: [
            categorizedData.Positive,
            categorizedData.Negative,
            categorizedData.Natural,
          ],
          backgroundColor: ["#4BC0C0", "#FF6384", "#36A2EB"],
          borderWidth: 1,
        },
      ],
    },
  });
}


async function renderBarChart(endpoint, chartId, title) {
  const data = await fetchData(endpoint);
  const counts = [0, 0, 0, 0, 0];

  data.forEach((item) => {
    counts[0] += item.one || 0;
    counts[1] += item.two || 0;
    counts[2] += item.three || 0;
    counts[3] += item.four || 0;
    counts[4] += item.five || 0;
  });

  const ctx = document.getElementById(chartId).getContext("2d");
  new Chart(ctx, {
    type: "bar",
    data: {
      labels: ["One", "Two", "Three", "Four", "Five"],
      datasets: [
        {
          label: title,
          data: counts,
          backgroundColor: "#36A2EB",
          borderWidth: 1,
        },
      ],
    },
  });
}


renderPieChart();
renderBarChart("/api/data/column_b", "barChartB", "Column B Data");
renderBarChart("/api/data/column_c", "barChartC", "Column C Data");
renderBarChart("/api/data/column_d", "barChartD", "Column D Data");
renderBarChart("/api/data/column_e", "barChartE", "Column E Data");
