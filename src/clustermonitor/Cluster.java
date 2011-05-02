package clustermonitor;

import java.util.ArrayList;

import clustermonitor.Rule.Action;
import clustermonitor.Rule.Comparison;

public class Cluster implements Monitorable {

	private ArrayList<Server> _servers;
	private String _clusterName;
	private RuleManager _ruleManager;

	Cluster(String clusterName) {

		_clusterName = clusterName;
		_ruleManager = new RuleManager();
		
		// instantiate servers
		_servers = new ArrayList<Server>();
	}

	void addServer(Server server) {

		if (!_servers.contains(server)) {
			_servers.add(server);
		}

	}

	String getName() {
		return this._clusterName;
	}

	int getServerCount() {
		return _servers.size();
	}

	int getActiveServerCount() {
		int count = 0;
		for (Server s : _servers) {
			if (s.isActive()) {
				count ++;
			}
		}
		return count;
	}


	public void addRule(String metric,
			Comparison comp, double value, long duration, Action action) {
		Rule r = new Rule(this, metric, comp, value, duration, action);
		_ruleManager.addRule(r);
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
		+ ((_clusterName == null) ? 0 : _clusterName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cluster other = (Cluster) obj;
		if (_clusterName == null) {
			if (other._clusterName != null)
				return false;
		} else if (!_clusterName.equals(other._clusterName))
			return false;
		return true;
	}

	@Override
	public void getPerformanceMetrics(PerformanceMetrics performanceMetrics) {
				
		// get performance metrics for all servers in cluster
		for (Server s : _servers) {

			// only if it is running right now
			if (s.isActive()) {
				
				PerformanceMetrics pmc = new PerformanceMetrics(performanceMetrics);
				s.getPerformanceMetrics(pmc);
				performanceMetrics.addMetricValues(pmc);

			}
		}

	}
}